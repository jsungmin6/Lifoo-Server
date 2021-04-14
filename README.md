# Lifoo-Server

-[앱 개발 프로젝트] Lifoo-Server (2021.01.05 ~ 2020.04.03)

### 1. 소개

- 음식사진을 통한 소통 SNS 앱

- [API 명세서](https://documenter.getpostman.com/view/11807633/TWDXmvw7)
- [ERD 설계](https://aquerytool.com:443/aquerymain/index/?rurl=0b5a02f2-5135-42d1-8e1b-0e43ece1ac94) 암호 : 732cx7

### 2. 기능

- 게시판
  - 사진, 글 업로드 기능
  - 다양한 이모지 표시 기능(like 좋아요)
  - 게시물 필터 기능 (랭킹, 나의 게시물)
  - 신고 기능
- 댓글
  - 신고 기능
  - 좋아요 기능
- 회원
  - 카카오 로그인 (서버만 구현)
  - 랜덤 닉네임 생성 기능
  - 로컬 이메일 로그인
- 알림
  - 내 게시물 관련 알림 기능 구현

### 3. 사용 기술

- Spring boot, Spring MVC, Spring Data Jpa, Nginx, EC2, RDS, Native Query, Jpql, jwt

### 4. 학습

- 패키지 구조
  - Service, Repository, Entity, Dto 패키지 구조에서 시간이 지남에따라 Entity별로 Service, Repository, Dto를 가지는 패키지 구조로 변경해 구조를 보다 알아보기 쉽도록 함.
- 순수한 Entity 유지
  - Entity는 핵심 비지니스 로직과 매핑에만 신경 씀
  - API 맞춤 로직이 Entity에 영향을 끼치지 않도록 Dto를 만들어 대신 처리함.
  - Entity를 순수하게 가져가서 사이드 이펙트 방지
- OSIV 종료 고려한 Service 계층에서 모든 비지니스 로직 처리
  - OSIV 전략은 트랜잭션 시작처럼 최초 데이터베이스 커넥션 시작 시점부터 API 응답이 끝날 때 까지 영속성 컨텍스트와 데이터베이스 커넥션을 유지 하지만 너무 오랜시간동안 데이터베이스 커넥션 리소스를 사용하기 때문에, 실시간 트래픽이 중요한 애플리케이션에서는 커넥션이 모자랄 수 있다
- Controller, Service, Repository 역할 구분
  - Controller : http 요청 받기, http요청 반환, validation, Service 로직 호출
  - Service : Transaction 안에서 비지니스 로직 처리, Repository 호출, Entity -> Dto 변환
  - Repository : Entity or Dto 반환, 생명주기가 다른 복잡한 쿼리 분리 처리
- Spring MVC 패턴 이해와 사용
  - Spring MVC 프레임 워크 사용
  - 프론트 컨트롤러 패턴으로 공통 로직 처리
  - API 서버이기 때문에 body에 바로 json을 넣으므로 ViewResolver 와 View는 사용하지 않음.
- 서버 성능 최적화 고려
  - 지연로딩
    - 모든 toOne 관계를 지연로딩으로 설정. 쿼리 튜닝을 용이하게 함
    - 필요한 쿼리를 패치조인으로 처리해 1+N 쿼리 문제 해결
- Dto 사용
  - Entity를 외부에 노출하면 Entity의 변화에 따라 API 스펙도 변하는 애로사항 발생
  - Entity를 모두 Dto로 바꿔 처리
- 복잡한 쿼리 처리
  - from, join 절의 subquery사용시 jpql의 한계 발생
  - Native Query 사용
- 자바8 기능 연습
  - optional
  - stream

### 5. UI

<img src="https://user-images.githubusercontent.com/59005171/114740627-fbc27080-9d84-11eb-93e8-c929e40d694b.PNG" width="300" height="600">
<img src="https://user-images.githubusercontent.com/59005171/114740742-17c61200-9d85-11eb-8d4c-ac3586eddb93.PNG " width="300" height="600">
<img src="https://user-images.githubusercontent.com/59005171/114740746-18f73f00-9d85-11eb-8f4a-daea9a22fb45.PNG " width="300" height="600">
<img src="https://user-images.githubusercontent.com/59005171/114740748-18f73f00-9d85-11eb-8459-8ad80de1f102.PNG" width="300" height="600">
<img src="https://user-images.githubusercontent.com/59005171/114740753-198fd580-9d85-11eb-9f74-ecec0d76b7ea.PNG" width="300" height="600">
