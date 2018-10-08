package com.imooc.imoocmuxinnetty.mapper;

import com.imooc.imoocmuxinnetty.pojo.Users;
import com.imooc.imoocmuxinnetty.pojo.vo.FriendRequestVO;
import com.imooc.imoocmuxinnetty.pojo.vo.MyFriendsVO;
import com.imooc.imoocmuxinnetty.utils.MyMapper;

import java.util.List;


public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}