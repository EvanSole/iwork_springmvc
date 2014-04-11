package com.iwork.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassUtil {

	public static Set<String> getClasses(File file) throws IOException {
		String fileName = file.getName();

		if (fileName.endsWith(".java")) {
			fileName = fileName.substring(0, fileName.length() - 5);
		}

		return getClasses(new FileReader(file), fileName);
	}

	public static Set<String> getClasses(Reader reader, String className)
		throws IOException {

		Set<String> classes = new HashSet<String>();

		StreamTokenizer st = new StreamTokenizer(new BufferedReader(reader));

		_setupParseTableForAnnotationProcessing(st);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_WORD) {
				if (st.sval.equals("class") || st.sval.equals("enum") ||
					st.sval.equals("interface") ||
					st.sval.equals("@interface")) {

					break;
				}
				else if (st.sval.startsWith("@")) {
					st.ordinaryChar(' ');
					st.wordChars('=', '=');

					String[] las = _processAnnotation(st.sval, st);

					for (int i = 0; i < las.length; i++) {
						classes.add(las[i]);
					}

					_setupParseTableForAnnotationProcessing(st);
				}
			}
		}

		_setupParseTable(st);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_WORD) {
				if (st.sval.indexOf('.') >= 0) {
					classes.add(st.sval.substring(0, st.sval.indexOf('.')));
				}
				else {
					classes.add(st.sval);
				}
			}
			else if (st.ttype != StreamTokenizer.TT_NUMBER &&
					 st.ttype != StreamTokenizer.TT_EOL) {

				if (Character.isUpperCase((char)st.ttype)) {
					classes.add(String.valueOf((char)st.ttype));
				}
			}
		}

		classes.remove(className);

		return classes;
	}

	public static boolean isSubclass(Class<?> a, Class<?> b) {
		if (a == b) {
			return true;
		}

		if (a == null || b == null) {
			return false;
		}

		for (Class<?> x = a; x != null; x = x.getSuperclass()) {
			if (x == b) {
				return true;
			}

			if (b.isInterface()) {
				Class<?>[] interfaces = x.getInterfaces();

				for (int i = 0; i < interfaces.length; i++) {
					if (isSubclass(interfaces[i], b)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isSubclass(Class<?> a, String s) {
		if (a == null || s == null) {
			return false;
		}

		if (a.getName().equals(s)) {
			return true;
		}

		for (Class<?> x = a; x != null; x = x.getSuperclass()) {
			if (x.getName().equals(s)) {
				return true;
			}

			Class<?>[] interfaces = x.getInterfaces();

			for (int i = 0; i < interfaces.length; i++) {
				if (isSubclass(interfaces[i], s)) {
					return true;
				}
			}
		}

		return false;
	}

	private static String[] _processAnnotation(String s, StreamTokenizer st)
		throws IOException {

		s = s.trim();

		List<String> tokens = new ArrayList<String>();

		Matcher annotationNameMatcher = _ANNOTATION_NAME_REGEXP.matcher(s);
		Matcher annotationParametersMatcher =
			_ANNOTATION_PARAMETERS_REGEXP.matcher(s);

		if (annotationNameMatcher.matches()) {
			String annotationName = annotationNameMatcher.group();

			tokens.add(annotationName.replace("@", ""));
		}
		else if (annotationParametersMatcher.matches()) {
			if (!s.trim().endsWith(")")) {
				while (st.nextToken() != StreamTokenizer.TT_EOF) {
					if (st.ttype == StreamTokenizer.TT_WORD) {
						s += st.sval;
						if (s.trim().endsWith(")")) {
							break;
						}
					}
				}
			}

			annotationParametersMatcher =
				_ANNOTATION_PARAMETERS_REGEXP.matcher(s);

			if (annotationParametersMatcher.matches()) {
				String annotationName =
					annotationParametersMatcher.group(1);
				String annotationParameters =
					annotationParametersMatcher.group(2);

				tokens.add(annotationName.replace("@", ""));

				tokens = _processAnnotationParameters(
					annotationParameters,tokens);
			}
		}

		return tokens.toArray(new String[tokens.size()]);
	}

	private static List<String> _processAnnotationParameters(
			String s, List<String> tokens)
		throws IOException {

		StreamTokenizer st = new StreamTokenizer(new StringReader(s));

		_setupParseTable(st);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_WORD) {
				if (st.sval.indexOf('.') >= 0) {
					tokens.add(st.sval.substring(0, st.sval.indexOf('.')));
				}
				else {
					tokens.add(st.sval);
				}
			}
			else if ((st.ttype != StreamTokenizer.TT_NUMBER) &&
					 (st.ttype != StreamTokenizer.TT_EOL)) {

				if (Character.isUpperCase((char)st.ttype)) {
					tokens.add(String.valueOf((char)st.ttype));
				}
			}
		}

		return tokens;
	}

	private static void _setupParseTable(StreamTokenizer st) {
		st.resetSyntax();
		st.slashSlashComments(true);
		st.slashStarComments(true);
		st.wordChars('a', 'z');
		st.wordChars('A', 'Z');
		st.wordChars('.', '.');
		st.wordChars('0', '9');
		st.wordChars('_', '_');
		st.lowerCaseMode(false);
		st.eolIsSignificant(false);
		st.quoteChar('"');
		st.quoteChar('\'');
		st.parseNumbers();
	}

	private static void _setupParseTableForAnnotationProcessing(
		StreamTokenizer st) {

		_setupParseTable(st);

		st.wordChars('@', '@');
		st.wordChars('(', '(');
		st.wordChars(')', ')');
		st.wordChars('{', '{');
		st.wordChars('}', '}');
		st.wordChars(',',',');
	}

	private static final Pattern _ANNOTATION_NAME_REGEXP =
		Pattern.compile("@(\\w+)$");

	private static final Pattern _ANNOTATION_PARAMETERS_REGEXP =
		Pattern.compile("@(\\w+)\\({0,1}\\{{0,1}([^)}]+)\\}{0,1}\\){0,1}");

}