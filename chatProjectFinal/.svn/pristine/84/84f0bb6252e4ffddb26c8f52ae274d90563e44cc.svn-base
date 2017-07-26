package chat.service;

import java.util.List;

import chat.domain.Category;
import chat.domain.Room;


public interface RoomService {

	/**
	 * 단일 방 정보 조회
	 * @param roomNumber 방 번호
	 * @return 해당하는 방 정보
	 */
	Room retrieveRoom(String roomNumber);
	
	/**
	 * 전체 방 목록 조회
	 * @param startNum 페이징 startNumber
	 * @param endNum 페이징 endNumber
	 * @return 전체 방 목록
	 */
	List<Room> retrieveRoomAllByRowNum(String more);
	
	/**
	 * 카테고리별 방 목록 조회
	 * @param category 카테고리
	 * @param startNum 페이징 startNumber
	 * @param endNum 페이징 endNumber
	 * @return 카테고리에 해당하는 방 목록
	 */
	List<Room> retrieveRoomByCategory(Category category, String more);
	
	/**
	 * 해당 유저가 생성한 방 조회
	 * @param loginId 사용자 로그인 아이디
	 * @return 사용자가 방장인 방 정보
	 */
	Room retrieveRoomByAdmin(String loginId);
	
	/**
	 * 키워드로 방 목록 조회
	 * @param keyword 검색 키워드
	 * @param startNum 페이징 startNumber
	 * @param endNum 페이징 endNumber
	 * @return 키워드로 검색한 방 목록
	 */
	List<Room> retrieveRoomByTitleForSearch(String keyword,String more);
	
	/**
	 * 신규 방 등록
	 * @param room 생성되는 방 정보
	 */
	void registerRoom(Room room);
	
	/**
	 * 방 정보 설정 변경
	 * @param room 수정 될 방 정보
	 */
	void modifyRoom(Room room);
	
}
