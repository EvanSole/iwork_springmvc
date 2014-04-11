package com.iwork.system.service;

import java.util.Set;

import com.iwork.system.entity.User;

public interface UserService {

	/***
	 * 创建账户
	 * @param user
	 * @return
	 */
	public User createUser(User user); 

	/***
	 * 修改密码
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(Long userId, String newPassword);

	/***
	 * 添加用户-角色关系
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(Long userId, Long... roleIds);

	/***
	 * 移除用户-角色关系
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);

	/***
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);

	/***
	 * 根据用户名查找其角色
	 * @param userName
	 * @return
	 */
	public Set<String> findRoles(String userName);

	/***
	 * 根据用户名查找其权限
	 * @param userName
	 * @return
	 */
	public Set<String> findPermissions(String userName); 

}
