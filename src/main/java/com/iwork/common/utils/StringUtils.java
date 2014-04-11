package com.iwork.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class StringUtils
{
    /**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;

    /**
     * The empty String <code>""</code>.
     * @since 2.0
     */
    public static final String EMPTY = "";

    // IndexOf
    //-----------------------------------------------------------------------
    /**
     * <p>Finds the first index within a String, handling <code>null</code>.
     * This method uses {@link String#indexOf(int)}.</p>
     *
     * <p>A <code>null</code> or empty ("") String will return <code>-1</code>.</p>
     *
     * <pre>
     * StringUtils.indexOf(null, *)         = -1
     * StringUtils.indexOf("", *)           = -1
     * StringUtils.indexOf("aabaabaa", 'a') = 0
     * StringUtils.indexOf("aabaabaa", 'b') = 2
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchChar  the character to find
     * @return the first index of the search character,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, char searchChar)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        return str.indexOf(searchChar);
    }

    /**
     * <p>Finds the first index within a String from a start position,
     * handling <code>null</code>.
     * This method uses {@link String#indexOf(int, int)}.</p>
     *
     * <p>A <code>null</code> or empty ("") String will return <code>-1</code>.
     * A negative start position is treated as zero.
     * A start position greater than the string length returns <code>-1</code>.</p>
     *
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf("", *, *)            = -1
     * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
     * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
     * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
     * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchChar  the character to find
     * @param startPos  the start position, negative treated as zero
     * @return the first index of the search character,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, char searchChar, int startPos)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        return str.indexOf(searchChar, startPos);
    }

    /**
     * <p>Finds the first index within a String, handling <code>null</code>.
     * This method uses {@link String#indexOf(String)}.</p>
     *
     * <p>A <code>null</code> String will return <code>-1</code>.</p>
     *
     * <pre>
     * StringUtils.indexOf(null, *)          = -1
     * StringUtils.indexOf(*, null)          = -1
     * StringUtils.indexOf("", "")           = 0
     * StringUtils.indexOf("aabaabaa", "a")  = 0
     * StringUtils.indexOf("aabaabaa", "b")  = 2
     * StringUtils.indexOf("aabaabaa", "ab") = 1
     * StringUtils.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return the first index of the search String,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int indexOf(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        return str.indexOf(searchStr);
    }

    // LastIndexOf
    //-----------------------------------------------------------------------
    /**
     * <p>Finds the last index within a String, handling <code>null</code>.
     * This method uses {@link String#lastIndexOf(int)}.</p>
     *
     * <p>A <code>null</code> or empty ("") String will return <code>-1</code>.</p>
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *)         = -1
     * StringUtils.lastIndexOf("", *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
     * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchChar  the character to find
     * @return the last index of the search character,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, char searchChar)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        return str.lastIndexOf(searchChar);
    }

    /**
     * <p>Finds the last index within a String from a start position,
     * handling <code>null</code>.
     * This method uses {@link String#lastIndexOf(int, int)}.</p>
     *
     * <p>A <code>null</code> or empty ("") String will return <code>-1</code>.
     * A negative start position returns <code>-1</code>.
     * A start position greater than the string length searches the whole string.</p>
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf("", *,  *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'b', 8)  = 5
     * StringUtils.lastIndexOf("aabaabaa", 'b', 4)  = 2
     * StringUtils.lastIndexOf("aabaabaa", 'b', 0)  = -1
     * StringUtils.lastIndexOf("aabaabaa", 'b', 9)  = 5
     * StringUtils.lastIndexOf("aabaabaa", 'b', -1) = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a', 0)  = 0
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchChar  the character to find
     * @param startPos  the start position
     * @return the last index of the search character,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, char searchChar, int startPos)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        return str.lastIndexOf(searchChar, startPos);
    }

    /**
     * <p>Finds the last index within a String, handling <code>null</code>.
     * This method uses {@link String#lastIndexOf(String)}.</p>
     *
     * <p>A <code>null</code> String will return <code>-1</code>.</p>
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *)          = -1
     * StringUtils.lastIndexOf(*, null)          = -1
     * StringUtils.lastIndexOf("", "")           = 0
     * StringUtils.lastIndexOf("aabaabaa", "a")  = 0
     * StringUtils.lastIndexOf("aabaabaa", "b")  = 2
     * StringUtils.lastIndexOf("aabaabaa", "ab") = 1
     * StringUtils.lastIndexOf("aabaabaa", "")   = 8
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return the last index of the search String,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        return str.lastIndexOf(searchStr);
    }

    /**
     * <p>Finds the first index within a String, handling <code>null</code>.
     * This method uses {@link String#lastIndexOf(String, int)}.</p>
     *
     * <p>A <code>null</code> String will return <code>-1</code>.
     * A negative start position returns <code>-1</code>.
     * An empty ("") search String always matches unless the start position is negative.
     * A start position greater than the string length searches the whole string.</p>
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf(*, null, *)          = -1
     * StringUtils.lastIndexOf("aabaabaa", "a", 8)  = 7
     * StringUtils.lastIndexOf("aabaabaa", "b", 8)  = 5
     * StringUtils.lastIndexOf("aabaabaa", "ab", 8) = 4
     * StringUtils.lastIndexOf("aabaabaa", "b", 9)  = 5
     * StringUtils.lastIndexOf("aabaabaa", "b", -1) = -1
     * StringUtils.lastIndexOf("aabaabaa", "a", 0)  = 0
     * StringUtils.lastIndexOf("aabaabaa", "b", 0)  = -1
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @param startPos  the start position, negative treated as zero
     * @return the first index of the search String,
     *  -1 if no match or <code>null</code> string input
     * @since 2.0
     */
    public static int lastIndexOf(String str, String searchStr, int startPos)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        return str.lastIndexOf(searchStr, startPos);
    }

    // Contains
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if String contains a search character, handling <code>null</code>.
     * This method uses {@link String#indexOf(int)}.</p>
     *
     * <p>A <code>null</code> or empty ("") String will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.contains(null, *)    = false
     * StringUtils.contains("", *)      = false
     * StringUtils.contains("abc", 'a') = true
     * StringUtils.contains("abc", 'z') = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchChar  the character to find
     * @return true if the String contains the search character,
     *  false if not or <code>null</code> string input
     * @since 2.0
     */
    public static boolean contains(String str, char searchChar)
    {
        if (isEmpty(str))
        {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    /**
     * <p>Checks if String contains a search String, handling <code>null</code>.
     * This method uses {@link String#indexOf(String)}.</p>
     *
     * <p>A <code>null</code> String will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return true if the String contains the search String,
     *  false if not or <code>null</code> string input
     * @since 2.0
     */
    public static boolean contains(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    // Substring
    //-----------------------------------------------------------------------
    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>A <code>null</code> String will return <code>null</code>.
     * An empty ("") String will return "".</p>
     *
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position, <code>null</code> if null String input
     */
    public static String substring(String str, int start)
    {
        if (str == null)
        {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0)
        {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return EMPTY;
        }

        return str.substring(start);
    }

    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start/end <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>The returned substring starts with the character in the <code>start</code>
     * position and ends before the <code>end</code> position. All position counting is
     * zero-based -- i.e., to start at the beginning of the string use
     * <code>start = 0</code>. Negative start and end positions can be used to
     * specify offsets relative to the end of the String.</p>
     *
     * <p>If <code>start</code> is not strictly to the left of <code>end</code>, ""
     * is returned.</p>
     *
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @param end  the position to end at (exclusive), negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position to end positon,
     *  <code>null</code> if null String input
     */
    public static String substring(String str, int start, int end)
    {
        if (str == null)
        {
            return null;
        }

        // handle negatives
        if (end < 0)
        {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0)
        {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length())
        {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end)
        {
            return EMPTY;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    // Left/Right/Mid
    //-----------------------------------------------------------------------
    /**
     * <p>Gets the leftmost <code>len</code> characters of a String.</p>
     *
     * <p>If <code>len</code> characters are not available, or the
     * String is <code>null</code>, the String will be returned without
     * an exception. An exception is thrown if len is negative.</p>
     *
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = ""
     * StringUtils.left("", *)      = ""
     * StringUtils.left("abc", 0)   = ""
     * StringUtils.left("abc", 2)   = "ab"
     * StringUtils.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  the String to get the leftmost characters from, may be null
     * @param len  the length of the required String, must be zero or positive
     * @return the leftmost characters, <code>null</code> if null String input
     */
    public static String left(String str, int len)
    {
        if (str == null)
        {
            return null;
        }
        if (len < 0)
        {
            return EMPTY;
        }
        if (str.length() <= len)
        {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * <p>Gets the rightmost <code>len</code> characters of a String.</p>
     *
     * <p>If <code>len</code> characters are not available, or the String
     * is <code>null</code>, the String will be returned without an
     * an exception. An exception is thrown if len is negative.</p>
     *
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = ""
     * StringUtils.right("", *)      = ""
     * StringUtils.right("abc", 0)   = ""
     * StringUtils.right("abc", 2)   = "bc"
     * StringUtils.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  the String to get the rightmost characters from, may be null
     * @param len  the length of the required String, must be zero or positive
     * @return the rightmost characters, <code>null</code> if null String input
     */
    public static String right(String str, int len)
    {
        if (str == null)
        {
            return null;
        }
        if (len < 0)
        {
            return EMPTY;
        }
        if (str.length() <= len)
        {
            return str;
        }
        return str.substring(str.length() - len);
    }

    /**
     * <p>Gets <code>len</code> characters from the middle of a String.</p>
     *
     * <p>If <code>len</code> characters are not available, the remainder
     * of the String will be returned without an exception. If the
     * String is <code>null</code>, <code>null</code> will be returned.
     * An exception is thrown if len is negative.</p>
     *
     * <pre>
     * StringUtils.mid(null, *, *)    = null
     * StringUtils.mid(*, *, -ve)     = ""
     * StringUtils.mid("", 0, *)      = ""
     * StringUtils.mid("abc", 0, 2)   = "ab"
     * StringUtils.mid("abc", 0, 4)   = "abc"
     * StringUtils.mid("abc", 2, 4)   = "c"
     * StringUtils.mid("abc", 4, 2)   = ""
     * StringUtils.mid("abc", -2, 2)  = "ab"
     * </pre>
     *
     * @param str  the String to get the characters from, may be null
     * @param pos  the position to start from, negative treated as zero
     * @param len  the length of the required String, must be zero or positive
     * @return the middle characters, <code>null</code> if null String input
     */
    public static String mid(String str, int pos, int len)
    {
        if (str == null)
        {
            return null;
        }
        if (len < 0 || pos > str.length())
        {
            return EMPTY;
        }
        if (pos < 0)
        {
            pos = 0;
        }
        if (str.length() <= (pos + len))
        {
            return str.substring(pos);
        }
        return str.substring(pos, pos + len);
    }

    // Joining
    //-----------------------------------------------------------------------
    /**
     * <p>Concatenates elements of an array into a single String.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.concatenate(null)            = null
     * StringUtils.concatenate([])              = ""
     * StringUtils.concatenate([null])          = ""
     * StringUtils.concatenate(["a", "b", "c"]) = "abc"
     * StringUtils.concatenate([null, "", "a"]) = "a"
     * </pre>
     *
     * @param array  the array of values to concatenate, may be null
     * @return the concatenated String, <code>null</code> if null array input
     * @deprecated Use the better named {@link #join(Object[])} instead.
     *             Method will be removed in Commons Lang 3.0.
     */
    @Deprecated
	public static String concatenate(Object[] array)
    {
        return join(array, null);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No separator is added to the joined String.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a", "b", "c"]) = "abc"
     * StringUtils.join([null, "", "a"]) = "a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array)
    {
        return join(array, null);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator)
    {
        if (array == null)
        {
            return null;
        }

        return join(array, separator, 0, array.length);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex)
    {
        if (array == null)
        {
            return null;
        }
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0)
        {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++)
        {
            if (i > startIndex)
            {
                buf.append(separator);
            }
            if (array[i] != null)
            {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator)
    {
        if (array == null)
        {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex)
    {
        if (array == null)
        {
            return null;
        }
        if (separator == null)
        {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0)
        {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length());

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++)
        {
            if (i > startIndex)
            {
                buf.append(separator);
            }
            if (array[i] != null)
            {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    // Delete
    //-----------------------------------------------------------------------
    /**
     * <p>Deletes all whitespaces from a String as defined by
     * {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.deleteWhitespace(null)         = null
     * StringUtils.deleteWhitespace("")           = ""
     * StringUtils.deleteWhitespace("abc")        = "abc"
     * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  the String to delete whitespace from, may be null
     * @return the String without whitespaces, <code>null</code> if null String input
     */
    public static String deleteWhitespace(String str)
    {
        if (isEmpty(str))
        {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz)
        {
            return str;
        }
        return new String(chs, 0, count);
    }

    // Remove
    //-----------------------------------------------------------------------
    /**
     * <p>Removes a substring only if it is at the begining of a source string,
     * otherwise returns the source string.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.
     * A <code>null</code> search string will return the source string.</p>
     *
     * <pre>
     * StringUtils.removeStart(null, *)      = null
     * StringUtils.removeStart("", *)        = ""
     * StringUtils.removeStart(*, null)      = *
     * StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
     * StringUtils.removeStart("domain.com", "www.")       = "domain.com"
     * StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeStart("abc", "")    = "abc"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @param remove  the String to search for and remove, may be null
     * @return the substring with the string removed if found,
     *  <code>null</code> if null String input
     * @since 2.1
     */
    public static String removeStart(String str, String remove)
    {
        if (isEmpty(str) || isEmpty(remove))
        {
            return str;
        }
        if (str.startsWith(remove))
        {
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * <p>Removes a substring only if it is at the end of a source string,
     * otherwise returns the source string.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.
     * A <code>null</code> search string will return the source string.</p>
     *
     * <pre>
     * StringUtils.removeEnd(null, *)      = null
     * StringUtils.removeEnd("", *)        = ""
     * StringUtils.removeEnd(*, null)      = *
     * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @param remove  the String to search for and remove, may be null
     * @return the substring with the string removed if found,
     *  <code>null</code> if null String input
     * @since 2.1
     */
    public static String removeEnd(String str, String remove)
    {
        if (isEmpty(str) || isEmpty(remove))
        {
            return str;
        }
        if (str.endsWith(remove))
        {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * <p>Removes all occurrences of a character from within the source string.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.</p>
     *
     * <pre>
     * StringUtils.remove(null, *)       = null
     * StringUtils.remove("", *)         = ""
     * StringUtils.remove("queued", 'u') = "qeed"
     * StringUtils.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @param remove  the char to search for and remove, may be null
     * @return the substring with the char removed if found,
     *  <code>null</code> if null String input
     * @since 2.1
     */
    public static String remove(String str, char remove)
    {
        if (isEmpty(str) || str.indexOf(remove) == -1)
        {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] != remove)
            {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str)
    {
        return !StringUtils.isEmpty(str);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str)
    {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if ((Character.isWhitespace(str.charAt(i)) == false))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str)
    {
        return !StringUtils.isBlank(str);
    }

    /**
     * <p>Compares two Strings, returning <code>true</code> if they are equal.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case sensitive.</p>
     *
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     *
     * @see java.lang.String#equals(Object)
     * @param str1  the first String, may be null
     * @param str2  the second String, may be null
     * @return <code>true</code> if the Strings are equal, case sensitive, or
     *  both <code>null</code>
     */
    public static boolean equals(String str1, String str2)
    {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * <p>Compares two Strings, returning <code>true</code> if they are equal ignoring
     * the case.</p>
     *
     * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered equal. Comparison is case insensitive.</p>
     *
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @see java.lang.String#equalsIgnoreCase(String)
     * @param str1  the first String, may be null
     * @param str2  the second String, may be null
     * @return <code>true</code> if the Strings are equal, case insensitive, or
     *  both <code>null</code>
     */
    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    // Replacing
    //-----------------------------------------------------------------------
    /**
     * <p>Replaces a String with another String inside a larger String, once.</p>
     *
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     *
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce("", *, *)          = ""
     * StringUtils.replaceOnce("any", null, *)    = "any"
     * StringUtils.replaceOnce("any", *, null)    = "any"
     * StringUtils.replaceOnce("any", "", *)      = "any"
     * StringUtils.replaceOnce("aba", "a", null)  = "aba"
     * StringUtils.replaceOnce("aba", "a", "")    = "ba"
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     *
     * @see #replace(String text, String searchString, String replacement, int max)
     * @param text  text to search and replace in, may be null
     * @param searchString  the String to search for, may be null
     * @param replacement  the String to replace with, may be null
     * @return the text with any replacements processed,
     *  <code>null</code> if null String input
     */
    public static String replaceOnce(String text, String searchString, String replacement)
    {
        return replace(text, searchString, replacement, 1);
    }


    /**
     * <p>Replaces a String with another String inside a larger String,
     * for the first <code>max</code> values of the search String.</p>
     *
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     *
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text  text to search and replace in, may be null
     * @param searchString  the String to search for, may be null
     * @param replacement  the String to replace it with, may be null
     * @param max  maximum number of values to replace, or <code>-1</code> if no maximum
     * @return the text with any replacements processed,
     *  <code>null</code> if null String input
     */
    public static String replace(String text, String searchString, String replacement, int max)
    {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0)
        {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1)
        {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1)
        {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0)
            {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * <p>Left pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *)   = null
     * StringUtils.leftPad("", 3)     = "   "
     * StringUtils.leftPad("bat", 3)  = "bat"
     * StringUtils.leftPad("bat", 5)  = "  bat"
     * StringUtils.leftPad("bat", 1)  = "bat"
     * StringUtils.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String leftPad(String str, int size)
    {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>Left pad a String with a specified character.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad("", 3, 'z')     = "zzz"
     * StringUtils.leftPad("bat", 3, 'z')  = "bat"
     * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtils.leftPad("bat", 1, 'z')  = "bat"
     * StringUtils.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String leftPad(String str, int size, char padChar)
    {
        if (str == null)
        {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0)
        {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT)
        {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    /**
     * <p>Left pad a String with a specified String.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String leftPad(String str, int size, String padStr)
    {
        if (str == null)
        {
            return null;
        }
        if (isEmpty(padStr))
        {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0)
        {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT)
        {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen)
        {
            return padStr.concat(str);
        }
        else if (pads < padLen)
        {
            return padStr.substring(0, pads).concat(str);
        }
        else
        {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++)
            {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     *
     * <pre>
     * StringUtils.padding(0, 'e')  = ""
     * StringUtils.padding(3, 'e')  = "eee"
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     *
     * <p>Note: this method doesn't not support padding with
     * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
     * as they require a pair of <code>char</code>s to be represented.
     * If you are needing to support full I18N of your applications
     * consider using {@link #repeat(String, int)} instead. 
     * </p>
     *
     * @param repeat  number of times to repeat delim
     * @param padChar  character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     * @see #repeat(String, int)
     */
    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException
    {
        if (repeat < 0)
        {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++)
        {
            buf[i] = padChar;
        }
        return new String(buf);
    }

    // Padding
    //-----------------------------------------------------------------------
    /**
     * <p>Repeat a String <code>repeat</code> times to form a
     * new String.</p>
     *
     * <pre>
     * StringUtils.repeat(null, 2) = null
     * StringUtils.repeat("", 0)   = ""
     * StringUtils.repeat("", 2)   = ""
     * StringUtils.repeat("a", 3)  = "aaa"
     * StringUtils.repeat("ab", 2) = "abab"
     * StringUtils.repeat("a", -2) = ""
     * </pre>
     *
     * @param str  the String to repeat, may be null
     * @param repeat  number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     *  <code>null</code> if null String input
     * @since 2.5
     */
    public static String repeat(String str, int repeat)
    {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null)
        {
            return null;
        }
        if (repeat <= 0)
        {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0)
        {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT)
        {
            return padding(repeat, str.charAt(0));
        }

        int outputLength = inputLength * repeat;
        switch (inputLength)
        {
        case 1:
            char ch = str.charAt(0);
            char[] output1 = new char[outputLength];
            for (int i = repeat - 1; i >= 0; i--)
            {
                output1[i] = ch;
            }
            return new String(output1);
        case 2:
            char ch0 = str.charAt(0);
            char ch1 = str.charAt(1);
            char[] output2 = new char[outputLength];
            for (int i = repeat * 2 - 2; i >= 0; i--, i--)
            {
                output2[i] = ch0;
                output2[i + 1] = ch1;
            }
            return new String(output2);
        default:
            StringBuffer buf = new StringBuffer(outputLength);
            for (int i = 0; i < repeat; i++)
            {
                buf.append(str);
            }
            return buf.toString();
        }
    }

    // Count matches
    //-----------------------------------------------------------------------
    /**
     * <p>Counts how many times the substring appears in the larger String.</p>
     *
     * <p>A <code>null</code> or empty ("") String input returns <code>0</code>.</p>
     *
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches("", *)         = 0
     * StringUtils.countMatches("abba", null)  = 0
     * StringUtils.countMatches("abba", "")    = 0
     * StringUtils.countMatches("abba", "a")   = 2
     * StringUtils.countMatches("abba", "ab")  = 1
     * StringUtils.countMatches("abba", "xxx") = 0
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param sub  the substring to count, may be null
     * @return the number of occurrences, 0 if either String is <code>null</code>
     */
    public static int countMatches(String str, String sub)
    {
        if (isEmpty(str) || isEmpty(sub))
        {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1)
        {
            count++;
            idx += sub.length();
        }
        return count;
    }

    // Character Tests
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if the String contains only unicode letters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha("")     = true
     * StringUtils.isAlpha("  ")   = false
     * StringUtils.isAlpha("abc")  = true
     * StringUtils.isAlpha("ab2c") = false
     * StringUtils.isAlpha("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters, and is non-null
     */
    public static boolean isAlpha(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if (Character.isLetter(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only unicode letters and
     * space (' ').</p>
     *
     * <p><code>null</code> will return <code>false</code>
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlphaSpace(null)   = false
     * StringUtils.isAlphaSpace("")     = true
     * StringUtils.isAlphaSpace("  ")   = true
     * StringUtils.isAlphaSpace("abc")  = true
     * StringUtils.isAlphaSpace("ab c") = true
     * StringUtils.isAlphaSpace("ab2c") = false
     * StringUtils.isAlphaSpace("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters and space,
     *  and is non-null
     */
    public static boolean isAlphaSpace(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if ((Character.isLetter(str.charAt(i)) == false) && (str.charAt(i) != ' '))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only unicode letters or digits.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric("")     = true
     * StringUtils.isAlphanumeric("  ")   = false
     * StringUtils.isAlphanumeric("abc")  = true
     * StringUtils.isAlphanumeric("ab c") = false
     * StringUtils.isAlphanumeric("ab2c") = true
     * StringUtils.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters or digits,
     *  and is non-null
     */
    public static boolean isAlphanumeric(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if (Character.isLetterOrDigit(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only unicode letters, digits
     * or space (<code>' '</code>).</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlphanumeric(null)   = false
     * StringUtils.isAlphanumeric("")     = true
     * StringUtils.isAlphanumeric("  ")   = true
     * StringUtils.isAlphanumeric("abc")  = true
     * StringUtils.isAlphanumeric("ab c") = true
     * StringUtils.isAlphanumeric("ab2c") = true
     * StringUtils.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters, digits or space,
     *  and is non-null
     */
    public static boolean isAlphanumericSpace(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if ((Character.isLetterOrDigit(str.charAt(i)) == false) && (str.charAt(i) != ' '))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only unicode digits.
     * A decimal point is not a unicode digit and returns false.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isNumeric(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if (Character.isDigit(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only unicode digits or space
     * (<code>' '</code>).
     * A decimal point is not a unicode digit and returns false.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = true
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = true
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains digits or space,
     *  and is non-null
     */
    public static boolean isNumericSpace(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if ((Character.isDigit(str.charAt(i)) == false) && (str.charAt(i) != ' '))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only whitespace.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isWhitespace(null)   = false
     * StringUtils.isWhitespace("")     = true
     * StringUtils.isWhitespace("  ")   = true
     * StringUtils.isWhitespace("abc")  = false
     * StringUtils.isWhitespace("ab2c") = false
     * StringUtils.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains whitespace, and is non-null
     * @since 2.0
     */
    public static boolean isWhitespace(String str)
    {
        if (str == null)
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if ((Character.isWhitespace(str.charAt(i)) == false))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only lowercase characters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.isAllLowerCase(null)   = false
     * StringUtils.isAllLowerCase("")     = false
     * StringUtils.isAllLowerCase("  ")   = false
     * StringUtils.isAllLowerCase("abc")  = true
     * StringUtils.isAllLowerCase("abC") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains lowercase characters, and is non-null
     * @since 2.5
     */
    public static boolean isAllLowerCase(String str)
    {
        if (str == null || isEmpty(str))
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if (Character.isLowerCase(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the String contains only uppercase characters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.isAllUpperCase(null)   = false
     * StringUtils.isAllUpperCase("")     = false
     * StringUtils.isAllUpperCase("  ")   = false
     * StringUtils.isAllUpperCase("ABC")  = true
     * StringUtils.isAllUpperCase("aBC") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains uppercase characters, and is non-null
     * @since 2.5
     */
    public static boolean isAllUpperCase(String str)
    {
        if (str == null || isEmpty(str))
        {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++)
        {
            if (Character.isUpperCase(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }

    // Reversing
    //-----------------------------------------------------------------------
    /**
     * <p>Reverses a String as per {@link StringBuffer#reverse()}.</p>
     *
     * <p>A <code>null</code> String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse("")    = ""
     * StringUtils.reverse("bat") = "tab"
     * </pre>
     *
     * @param str  the String to reverse, may be null
     * @return the reversed String, <code>null</code> if null String input
     */
    public static String reverse(String str)
    {
        if (str == null)
        {
            return null;
        }
        return new StringBuffer(str).reverse().toString();
    }
    
    public static String add(String s, String add) {
		return add(s, add, StringPool.COMMA);
	}

	public static String add(String s, String add, String delimiter) {
		return add(s, add, delimiter, false);
	}

	public static String add(
		String s, String add, String delimiter, boolean allowDuplicates) {

		if ((add == null) || (delimiter == null)) {
			return null;
		}

		if (s == null) {
			s = StringPool.BLANK;
		}

		if (allowDuplicates || !contains(s, add, delimiter)) {
			StringBuilder sb = new StringBuilder();

			sb.append(s);

			if (Validator.isNull(s) || s.endsWith(delimiter)) {
				sb.append(add);
				sb.append(delimiter);
			}
			else {
				sb.append(delimiter);
				sb.append(add);
				sb.append(delimiter);
			}

			s = sb.toString();
		}

		return s;
	}

	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(
				0x0100 + (bytes[i] & 0x00FF)).substring(1);

			if (hex.length() < 2) {
				sb.append("0");
			}

			sb.append(hex);
		}

		return sb.toString();
	}


	public static int count(String s, String text) {
		if ((s == null) || (text == null)) {
			return 0;
		}

		int count = 0;

		int pos = s.indexOf(text);

		while (pos != -1) {
			pos = s.indexOf(text, pos + text.length());

			count++;
		}

		return count;
	}

	public static boolean endsWith(String s, char end) {
		return endsWith(s, (new Character(end)).toString());
	}

	public static boolean endsWith(String s, String end) {
		if ((s == null) || (end == null)) {
			return false;
		}

		if (end.length() > s.length()) {
			return false;
		}

		String temp = s.substring(s.length() - end.length(), s.length());

		if (temp.equalsIgnoreCase(end)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static String extractChars(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBuilder sb = new StringBuilder();

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (Validator.isChar(c[i])) {
				sb.append(c[i]);
			}
		}

		return sb.toString();
	}

	public static String extractDigits(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBuilder sb = new StringBuilder();

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (Validator.isDigit(c[i])) {
				sb.append(c[i]);
			}
		}

		return sb.toString();
	}

	public static String extractFirst(String s, String delimiter) {
		if (s == null) {
			return null;
		}
		else {
			String[] array = split(s, delimiter);

			if (array.length > 0) {
				return array[0];
			}
			else {
				return null;
			}
		}
	}

	public static String extractLast(String s, String delimiter) {
		if (s == null) {
			return null;
		}
		else {
			String[] array = split(s, delimiter);

			if (array.length > 0) {
				return array[array.length - 1];
			}
			else {
				return null;
			}
		}
	}

	public static String highlight(String s, String keywords) {
		return highlight(s, keywords, "<span class=\"highlight\">", "</span>");
	}

	public static String highlight(
		String s, String keywords, String highlight1, String highlight2) {

		if (s == null) {
			return null;
		}

		// The problem with using a regexp is that it searches the text in a
		// case insenstive manner but doens't replace the text in a case
		// insenstive manner. So the search results actually get messed up. The
		// best way is to actually parse the results.

		//return s.replaceAll(
		//	"(?i)" + keywords, highlight1 + keywords + highlight2);

		StringBuilder sb = new StringBuilder(StringPool.SPACE);

		StringTokenizer st = new StringTokenizer(s);

		while (st.hasMoreTokens()) {
			String token = st.nextToken();

			if (token.equalsIgnoreCase(keywords)) {
				sb.append(highlight1);
				sb.append(token);
				sb.append(highlight2);
			}
			else {
				sb.append(token);
			}

			if (st.hasMoreTokens()) {
				sb.append(StringPool.SPACE);
			}
		}

		return sb.toString();
	}

	public static String insert(String s, String insert, int offset) {
		if (s == null) {
			return null;
		}

		if (insert == null) {
			return s;
		}

		if (offset > s.length()) {
			offset = s.length();
		}

		StringBuilder sb = new StringBuilder(s);

		sb.insert(offset, insert);

		return sb.toString();
	}

	public static String lowerCase(String s) {
		if (s == null) {
			return null;
		}
		else {
			return s.toLowerCase();
		}
	}

	public static boolean matches(String s, String pattern) {
		String[] array = pattern.split("\\*");

		for (int i = 0; i < array.length; i++) {
			int pos = s.indexOf(array[i]);

			if (pos == -1) {
				return false;
			}

			s = s.substring(pos + array[i].length());
		}

		return true;
	}

	public static String merge(boolean[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(boolean[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String merge(double[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(double[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String merge(float[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(float[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String merge(int[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(int[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String merge(long[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(long[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String merge(short[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(short[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String merge(Collection<?> col) {
		return merge(col, StringPool.COMMA);
	}

	public static String merge(Collection<?> col, String delimiter) {
		if (col == null) {
			return null;
		}

		return merge(col.toArray(new Object[col.size()]), delimiter);
	}

	public static String merge(Object[] array) {
		return merge(array, StringPool.COMMA);
	}

	public static String merge(Object[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(String.valueOf(array[i]).trim());

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}


	public static String read(ClassLoader classLoader, String name)
		throws IOException {

		return read(classLoader, name, false);
	}

	public static String read(ClassLoader classLoader, String name, boolean all)
		throws IOException {

		if (all) {
			StringBuilder sb = new StringBuilder();

			Enumeration<URL> enu = classLoader.getResources(name);

			while (enu.hasMoreElements()) {
				URL url = enu.nextElement();

				InputStream is = url.openStream();

				if (is == null) {
					throw new IOException(
						"Unable to open resource at " + url.toString());
				}

				String s = read(is);

				if (s != null) {
					sb.append(s);
					sb.append(StringPool.NEW_LINE);
				}

				is.close();
			}

			return sb.toString().trim();
		}
		else {
			InputStream is = classLoader.getResourceAsStream(name);

			if (is == null) {
				throw new IOException(
					"Unable to open resource in class loader " + name);
			}

			String s = read(is);

			is.close();

			return s;
		}
	}

	public static String read(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line = null;

		while ((line = br.readLine()) != null) {
			sb.append(line).append('\n');
		}

		br.close();

		return sb.toString().trim();
	}

	public static String remove(String s, String remove) {
		return remove(s, remove, StringPool.COMMA);
	}

	public static String remove(String s, String remove, String delimiter) {
		if ((s == null) || (remove == null) || (delimiter == null)) {
			return null;
		}

		if (Validator.isNotNull(s) && !s.endsWith(delimiter)) {
			s += delimiter;
		}

		StringBuilder sb = new StringBuilder();

		sb.append(delimiter);
		sb.append(remove);
		sb.append(delimiter);

		String drd = sb.toString();

		sb = new StringBuilder();

		sb.append(remove);
		sb.append(delimiter);

		String rd = sb.toString();

		while (contains(s, remove, delimiter)) {
			int pos = s.indexOf(drd);

			if (pos == -1) {
				if (s.startsWith(rd)) {
					int x = remove.length() + delimiter.length();
					int y = s.length();

					s = s.substring(x, y);
				}
			}
			else {
				int x = pos + remove.length() + delimiter.length();
				int y = s.length();

				sb = new StringBuilder();

				sb.append(s.substring(0, pos));
				sb.append(s.substring(x, y));

				s = sb.toString();
			}
		}

		return s;
	}
	
	public static boolean contains(String s, String text, String delimiter) {
		if ((s == null) || (text == null) || (delimiter == null)) {
			return false;
		}

		StringBuilder sb = null;

		if (!s.endsWith(delimiter)) {
			sb = new StringBuilder();

			sb.append(s);
			sb.append(delimiter);

			s = sb.toString();
		}

		sb = new StringBuilder();

		sb.append(delimiter);
		sb.append(text);
		sb.append(delimiter);

		String dtd = sb.toString();

		int pos = s.indexOf(dtd);

		if (pos == -1) {
			sb = new StringBuilder();

			sb.append(text);
			sb.append(delimiter);

			String td = sb.toString();

			if (s.startsWith(td)) {
				return true;
			}

			return false;
		}

		return true;
	}


	public static String replace(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replace(oldSub, newSub);
	}

	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		// The number 5 is arbitrary and is used as extra padding to reduce
		// buffer expansion

		StringBuilder sb = new StringBuilder(s.length() + 5 * newSub.length());

		char[] charArray = s.toCharArray();

		for (char c : charArray) {
			if (c == oldSub) {
				sb.append(newSub);
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {

			// The number 5 is arbitrary and is used as extra padding to reduce
			// buffer expansion

			StringBuilder sb = new StringBuilder(
				s.length() + 5 * newSub.length());

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);

				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		}
		else {
			return s;
		}
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	public static String replace(
		String s, String[] oldSubs, String[] newSubs, boolean exactMatch) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		if (!exactMatch) {
			replace(s, oldSubs, newSubs);
		}
		else {
			for (int i = 0; i < oldSubs.length; i++) {
				s = s.replaceAll("\\b" + oldSubs[i] + "\\b" , newSubs[i]);
			}
		}

		return s;
	}

	public static String replaceFirst(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replaceFirst(String.valueOf(oldSub), String.valueOf(newSub));
	}

	public static String replaceFirst(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		return s.replaceFirst(String.valueOf(oldSub), newSub);
	}

	public static String replaceFirst(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		return s.replaceFirst(oldSub, newSub);
	}

	public static String replaceFirst(
		String s, String[] oldSubs, String[] newSubs) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceFirst(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Returns a string with replaced values. This method will replace all text
	 * in the given string, between the beginning and ending delimiter, with new
	 * values found in the given map. For example, if the string contained the
	 * text <code>[$HELLO$]</code>, and the beginning delimiter was
	 * <code>[$]</code>, and the ending delimiter was <code>$]</code>, and the
	 * values map had a key of <code>HELLO</code> that mapped to
	 * <code>WORLD</code>, then the replaced string will contain the text
	 * <code>[$WORLD$]</code>.
	 *
	 * @param		s the original string
	 * @param		begin the beginning delimiter
	 * @param		end the ending delimiter
	 * @param		values a map of old and new values
	 * @return		a string with replaced values
	 */
	public static String replaceValues(
		String s, String begin, String end, Map<String, String> values) {

		if ((s == null) || (begin == null) || (end == null) ||
			(values == null) || (values.size() == 0)) {

			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos, s.length()));

				break;
			}
			else {
				sb.append(s.substring(pos, x + begin.length()));

				String oldValue = s.substring(x + begin.length(), y);

				String newValue = values.get(oldValue);

				if (newValue == null) {
					newValue = oldValue;
				}

				sb.append(newValue);

				pos = y;
			}
		}

		return sb.toString();
	}


	public static String safePath(String path) {
		return replace(path, StringPool.DOUBLE_SLASH, StringPool.SLASH);
	}

	public static String shorten(String s) {
		return shorten(s, 20);
	}

	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	public static String shorten(String s, String suffix) {
		return shorten(s, 20, suffix);
	}

	public static String shorten(String s, int length, String suffix) {
		if ((s == null) || (suffix == null)) {
			return null;
		}

		if (s.length() > length) {
			for (int j = length; j >= 0; j--) {
				if (Character.isWhitespace(s.charAt(j))) {
					length = j;

					break;
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append(s.substring(0, length));
			sb.append(suffix);

			s =  sb.toString();
		}

		return s;
	}

	public static String[] split(String s) {
		return split(s, StringPool.COMMA);
	}

	public static String[] split(String s, String delimiter) {
		if ((Validator.isNull(s)) || (delimiter == null) ||
			(delimiter.equals(StringPool.BLANK))) {

			return new String[0];
		}

		s = s.trim();

		if (!s.endsWith(delimiter)) {
			StringBuilder sb = new StringBuilder();

			sb.append(s);
			sb.append(delimiter);

			s = sb.toString();
		}

		if (s.equals(delimiter)) {
			return new String[0];
		}

		List<String> nodeValues = new ArrayList<String>();

		if (delimiter.equals(StringPool.NEW_LINE) ||
			delimiter.equals(StringPool.RETURN)) {

			try {
				BufferedReader br = new BufferedReader(new StringReader(s));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}

				br.close();
			}
			catch (IOException ioe) {
				_logger.error(ioe.getMessage());
			}
		}
		else {
			int offset = 0;
			int pos = s.indexOf(delimiter, offset);

			while (pos != -1) {
				nodeValues.add(new String(s.substring(offset, pos)));

				offset = pos + delimiter.length();
				pos = s.indexOf(delimiter, offset);
			}
		}

		return nodeValues.toArray(new String[nodeValues.size()]);
	}

	public static boolean[] split(String s, boolean x) {
		return split(s, StringPool.COMMA, x);
	}

	public static boolean[] split(String s, String delimiter, boolean x) {
		String[] array = split(s, delimiter);
		boolean[] newArray = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			boolean value = x;

			try {
				value = Boolean.valueOf(array[i]).booleanValue();
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static double[] split(String s, double x) {
		return split(s, StringPool.COMMA, x);
	}

	public static double[] split(String s, String delimiter, double x) {
		String[] array = split(s, delimiter);
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			double value = x;

			try {
				value = Double.parseDouble(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static float[] split(String s, float x) {
		return split(s, StringPool.COMMA, x);
	}

	public static float[] split(String s, String delimiter, float x) {
		String[] array = split(s, delimiter);
		float[] newArray = new float[array.length];

		for (int i = 0; i < array.length; i++) {
			float value = x;

			try {
				value = Float.parseFloat(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static int[] split(String s, int x) {
		return split(s, StringPool.COMMA, x);
	}

	public static int[] split(String s, String delimiter, int x) {
		String[] array = split(s, delimiter);
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			int value = x;

			try {
				value = Integer.parseInt(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static long[] split(String s, long x) {
		return split(s, StringPool.COMMA, x);
	}

	public static long[] split(String s, String delimiter, long x) {
		String[] array = split(s, delimiter);
		long[] newArray = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			long value = x;

			try {
				value = Long.parseLong(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static short[] split(String s, short x) {
		return split(s, StringPool.COMMA, x);
	}

	public static short[] split(String s, String delimiter, short x) {
		String[] array = split(s, delimiter);
		short[] newArray = new short[array.length];

		for (int i = 0; i < array.length; i++) {
			short value = x;

			try {
				value = Short.parseShort(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static boolean startsWith(String s, char begin) {
		return startsWith(s, (new Character(begin)).toString());
	}

	public static boolean startsWith(String s, String start) {
		if ((s == null) || (start == null)) {
			return false;
		}

		if (start.length() > s.length()) {
			return false;
		}

		String temp = s.substring(0, start.length());

		if (temp.equalsIgnoreCase(start)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Return the number of starting letters that s1 and s2 have in common
	 * before they deviate.
	 *
	 * @param		s1 the first string
	 * @param		s2 the second string
	 *
	 * @return		the number of starting letters that s1 and s2 have in common
	 *				before they deviate
	 */
	public static int startsWithWeight(String s1, String s2) {
		if ((s1 == null) || (s2 == null)) {
			return 0;
		}

		char[] charArray1 = s1.toCharArray();
		char[] charArray2 = s2.toCharArray();

		int i = 0;

		for (; (i < charArray1.length) && (i < charArray2.length); i++) {
			if (charArray1[i] != charArray2[i]) {
				break;
			}
		}

		return i;
	}

	public static String stripBetween(String s, String begin, String end) {
		if ((s == null) || (begin == null) || (end == null)) {
			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos, s.length()));

				break;
			}
			else {
				sb.append(s.substring(pos, x));

				pos = y + end.length();
			}
		}

		return sb.toString();
	}

	public static String trim(String s) {
		return trim(s, null);
	}

	public static String trim(String s, char c) {
		return trim(s, new char[] {c});
	}

	public static String trim(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		char[] charArray = s.toCharArray();

		int len = charArray.length;

		int x = 0;
		int y = charArray.length;

		for (int i = 0; i < len; i++) {
			char c = charArray[i];

			if (_isTrimable(c, exceptions)) {
				x = i + 1;
			}
			else {
				break;
			}
		}

		for (int i = len - 1; i >= 0; i--) {
			char c = charArray[i];

			if (_isTrimable(c, exceptions)) {
				y = i;
			}
			else {
				break;
			}
		}

		if ((x != 0) || (y != len)) {
			return s.substring(x, y);
		}
		else {
			return s;
		}
	}

	public static String trimLeading(String s) {
		return trimLeading(s, null);
	}

	public static String trimLeading(String s, char c) {
		return trimLeading(s, new char[] {c});
	}

	public static String trimLeading(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		char[] charArray = s.toCharArray();

		int len = charArray.length;

		int x = 0;
		int y = charArray.length;

		for (int i = 0; i < len; i++) {
			char c = charArray[i];

			if (_isTrimable(c, exceptions)) {
				x = i + 1;
			}
			else {
				break;
			}
		}

		if ((x != 0) || (y != len)) {
			return s.substring(x, y);
		}
		else {
			return s;
		}
	}

	public static String trimTrailing(String s) {
		return trimTrailing(s, null);
	}

	public static String trimTrailing(String s, char c) {
		return trimTrailing(s, new char[] {c});
	}

	public static String trimTrailing(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		char[] charArray = s.toCharArray();

		int len = charArray.length;

		int x = 0;
		int y = charArray.length;

		for (int i = len - 1; i >= 0; i--) {
			char c = charArray[i];

			if (_isTrimable(c, exceptions)) {
				y = i;
			}
			else {
				break;
			}
		}

		if ((x != 0) || (y != len)) {
			return s.substring(x, y);
		}
		else {
			return s;
		}
	}

	public static String upperCase(String s) {
		if (s == null) {
			return null;
		}
		else {
			return s.toUpperCase();
		}
	}

	public static String upperCaseFirstLetter(String s) {
		char[] chars = s.toCharArray();

		if ((chars[0] >= 97) && (chars[0] <= 122)) {
			chars[0] = (char)(chars[0] - 32);
		}

		return new String(chars);
	}

	public static String valueOf(Object obj) {
		return String.valueOf(obj);
	}

	public static String wrap(String text) {
		return wrap(text, 80, StringPool.NEW_LINE);
	}

	public static String wrap(String text, int width, String lineSeparator) {
		if (text == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new StringReader(text));

			String s = StringPool.BLANK;

			while ((s = br.readLine()) != null) {
				if (s.length() == 0) {
					sb.append(lineSeparator);
				}
				else {
					String[] tokens = s.split(StringPool.SPACE);
					boolean firstWord = true;
					int curLineLength = 0;

					for (int i = 0; i < tokens.length; i++) {
						if (!firstWord) {
							sb.append(StringPool.SPACE);
							curLineLength++;
						}

						if (firstWord) {
							sb.append(lineSeparator);
						}

						sb.append(tokens[i]);

						curLineLength += tokens[i].length();

						if (curLineLength >= width) {
							firstWord = true;
							curLineLength = 0;
						}
						else {
							firstWord = false;
						}
					}
				}
			}
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage());
		}

		return sb.toString();
	}

	private static boolean _isTrimable(char c, char[] exceptions) {
		if ((exceptions != null) && (exceptions.length > 0)) {
			for (int i = 0; i < exceptions.length; i++) {
				if (c == exceptions[i]) {
					return false;
				}
			}
		}

		return Character.isWhitespace(c);
	}

	private static Logger _logger = LoggerFactory.getLogger(StringUtils.class);

}
