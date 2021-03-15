# Lifoo-Server

[앱 개발 프로젝트] Lifoo-Server

### 1. 소개

- 음식사진 커뮤니티 앱

- [API 명세서](https://documenter.getpostman.com/view/11807633/TWDXmvw7)
- [ERD 설계](https://aquerytool.com:443/aquerymain/index/?rurl=0b5a02f2-5135-42d1-8e1b-0e43ece1ac94) 암호 : 732cx7

### 2. 기능

- 게시판
  - 사진, 글 업로드
  - 다양한 이모지 표시 기능(like 좋아요)
  - 게시물 필터 기능
  - 신고 기능 (예정)
- 댓글
  - 신고 기능 (예정)
  - 좋아요 기능 (예정)
- 회원
  - 카카오 로그인
  - 랜덤 닉네임 생성 기능
  - 회원가입

### 3. 기술

- Spring boot, Spring Data Jpa, Spring Sequrity, Nginx, EC2, RDS, QueryDsl(사용예정), Native Query, Jpql

### 4. 문제와 해결

- Entity default 입력
- EC2 memory 부족
- 랜덤 닉네임 메커니즘
