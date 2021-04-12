package ga.lifoo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum  BaseResponseStatus {

    // 2000 : 요청 성공
    SUCCESS(true, 2000, "요청에 성공하였습니다."),


    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_USERID(false, 2001, "유저 아이디 값을 확인해주세요."),
    EMPTY_JWT(false, 2010, "JWT를 입력해주세요."),
    EMPTY_EMAIL(false, 2020, "이메일을 입력해주세요."),
    INVALID_EMAIL(false, 2021, "이메일 형식을 확인해주세요."),
    EMPTY_CONFIRM_PASSWORD(false, 2031, "비밀번호 확인을 입력해주세요."),
    WRONG_PASSWORD(false, 2032, "비밀번호를 다시 입력해주세요."),
    DO_NOT_MATCH_PASSWORD(false, 2033, "비밀번호와 비밀번호확인 값이 일치하지 않습니다."),
    EMPTY_NICKNAME(false, 2040, "닉네임을 입력해주세요."),
    DO_NOT_MATCH_USERID(false, 2041, "회원 인덱스 값이 일치하지 않습니다."),
    EMPTY_LOGIN_TYPE(false, 2042, "sns 로그인 유형을 입력해주세요."),
    EMPTY_ADDRESS(false, 2043, "주소를 입력해주세요."),
    EMPTY_LATITUDE(false, 2044, "위도를 입력해주세요."),
    EMPTY_LONGITUDE(false, 2045, "경도를 입력해주세요."),
    EMPTY_STARTTIME(false, 2046, "시작시간을 입력해 주세요"),
    EMPTY_ENDTIME(false, 2047, "종료시간을 입력해 주세요"),
    EMPTY_DEVICETOKEN(false, 2300, "userDeviceToken을 입력해 주세요"),
    DO_NOT_MATCH_USERIDX(false, 2200, "JWT토큰의 userId와 PathVariable 의 userId가 일치하지 않습니다."),
    INVALID_APPLE(false, 2210, "유효하지 않은 애플 로그인 값 입니다"),


    // 3000 : Response 오류
    EMPTY_JWT_ERROR(false, 3000, "JWT를 입력해주세요."),
    EMPTY_TYPE_ERROR(false, 3001, "type을 입력해주세요."),
    EMPTY_NICNAME_ERROR(false, 3002, "nickname을 입력해주세요."),
    EMPTY_SIZE_ERROR(false, 3003, "size를 입력해주세요."),
    EMPTY_PAGE_ERROR(false, 3004, "page를 입력해주세요."),
    EMPTY_URL_ERROR(false, 3005, "사진or영상을 등록해주세요."),
    EMPTY_TITLE_ERROR(false, 3006, "게시물 제목을 입력해주세요."),
    EMPTY_POST_IDX_ERROR(false, 3007, "게시물 인덱스를 입력해주세요."),
    EMPTY_IMOGE_IDX_ERROR(false, 3008, "이모지 인덱스를 입력해주세요."),
    EMPTY_COMMENT_BODY_ERROR(false, 3009, "댓글내용을 입력해주세요."),
    EMPTY_COMMENT_IDX_ERROR(false, 3010, "댓글 인덱스를 입력해주세요."),
    EMPTY_REPORT_TYPE_ERROR(false, 3011, "신고 종류를 입력해주세요."),
    EMPTY_TARGET_ERROR(false, 3012, "대상 인덱스를 입력해주세요."),
    EMPTY_KEYWORD_ERROR(false, 3013, "검색어를 입력해주세요."),
    EMPTY_SNSID_ERROR(false, 3014, "snsId를 입력해주세요."),
    EMPTY_USERIDX_ERROR(false, 3015, "userIdx를 입력해주세요."),
    EMPTY_ACCESSTOKEN_ERROR(false, 3016, "access-token을 입력해 주세요."),
    DIFF_PASSWORD_CHECK(false, 3017, "2차 비밀번호가 다릅니다."),
    EMPTY_PASSWORD(false, 3018, "비밀번호를 입력해주세요"),
    EMPTY_PASSWORD_CHECK(false, 3019, "2차 비밀번호를 입력해주세요"),
    EMPTY_ID(false, 3020, "아이디를 입력해주세요"),
    NOT_MATCH_PASSWORD(false, 3021, "비밀번호가 일치하지 않습니다."),

    ALREADY_NICKNAME_ERROR(false, 3100, "이미 존재하는 닉네임 입니다."),
    ALREADY_USER_ERROR(false, 3101, "이미 존재하는 회원입니다."),
    ALREADY_REPORT_POST_ERROR(false, 3102, "이미 신고한 게시물 입니다."),
    ALREADY_REPORT_COMMENT_ERROR(false, 3103, "이미 신고한 댓글 입니다."),
    ALREADY_SNSID_ERROR(false, 3104, "이미 존재하는 snsId입니다."),

    INVALID_JWT(false, 3200, "유효하지 않은 JWT입니다."),
    NOT_EXIST_USER(false, 3201, "존재하지 않는 회원입니다."),
    NEW_USER(false, 3202, "신규 회원입니다."),
    INVALID_ACCESS_TOKEN(false, 3203, "유효하지 않은 access-token입니다."),
    INVALID_TYPE(false, 3204, "type 형식이 아닙니다."),
    INVALID_POST_TITLE(false, 3205, "제목 형식이 아닙니다."),
    INVALID_IMOGE(false, 3206, "존재하지 않는 이모지입니다."),
    INVALID_POST(false, 3207, "존재하지 않는 게시물입니다."),

    NOT_EXIST_POST_LIST(false, 3208, "게시물 목록이 없습니다."),
    NOT_EXIST_ALARM_LIST(false, 3209, "알림 목록이 없습니다."),
    NOT_EXIST_COMMENT_LIST(false, 3210, "댓글 목록이 없습니다."),
    NOT_EXIST_COMMENT(false, 3211, "존재하지 않는 댓글입니다."),
    INVALID_REPORT_TYPE(false, 3212, "신고 종류 형식이 아닙니다."),
    EXIST_ID(false, 3213, "중복된 아이디 입니다."),

    NO_AUTHORITY(false, 3300, "권한이 없습니다."),

    JSON_PROCESSING_ERROR(false, 3400, "JsonProcessingException 에러 입니다."),

    // 4000 : Database 오류
    SERVER_ERROR(false, 4000, "서버와의 통신에 실패하였습니다."),
    DATABASE_ERROR(false, 4001, "데이터베이스 연결에 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
