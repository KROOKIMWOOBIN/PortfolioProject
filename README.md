# template


# 📝 포트폴리오 프로젝트

## 설명

* 포트폴리오 프로젝트는 가장 기본 기능을 기반으로 본인만의 포트폴리오 프로젝트를 만들어보세요!
* 프로젝트 명칭은 변경해주시길 바랍니다!


* CRUD(Create, Read, Update, Delete) 데이터 조작 작업을 포함합니다.
* TEST코드 작성 및 단위 테스트를 진행합니다.
* 스프링 시큐리티를 활용합니다.


아래의 작업들은 필수로 구현해야하는 프로젝트 기능이며,

**본인만의 프로젝트 기능을 아래의 내용에 추가해서 작성해주세요!**

## 로그인

* [X]  스프링 시큐리티를 활용한 로그인 기능 구현
* [X]  테스트 코드 작성 및 통과

## 회원가입

* [X]  이메일 - 이메일 형식에 맞는지 검증
* [X]  휴대폰 번호 - 숫자와 하이폰으로 구성된 형식 검즘
* [X]  작성자 - 아이디 대소문자 및 한글 이름 검즘
* [X]  비밀번호 - 대소문자, 숫자 5개 이상, 특수문자 포함 2개 이상 검즘
* [X]  테스트 코드 작성 및 통과

## 게시글 등록

* [X]  제목 - 200글자 이하 제한
* [X]  내용 - 1000글자 이하 제한
* [X]  생성및 수정 시간 자동관리
* [X]  테스트 코드 작성 및 통과

## 게시글 수정

* [X]  생성일 기준 10일 이후 수정불가
* [X]  생성일 9일째 경고 알림(하루 후 수정 불가 알람)
* [ ]  테스트 코드 작성 및 통과

## 게시글 목록조회

[](https://github.com/jinmlee/Article-project#%EA%B2%8C%EC%8B%9C%EA%B8%80-%EB%AA%A9%EB%A1%9D%EC%A1%B0%ED%9A%8C)

* [ ]  생성일 기준 내림차순 오름차순 정렬
* [ ]  title 기준 부분 검색 가능
* [ ]  title 이 없을 경우 cratedAt 정렬 기준으로 표시
* [ ]  deletedAt 기준 삭제된 게시글 제외
* [ ]  테스트 코드 작성 및 통과

## 게시글 상세보기

* [ ]  수정 가능일 현재 날짜 기준 계산 및 표시
* [ ]  테스트 코드 작성 및 통과

## 게시글 삭제

* [ ]  Soft Delete 적용 deletedAt 사용하여 삭제처리
* [ ]  Hard Delete 적용
* [ ]  테스트 코드 작성 및 통과

## 📌 추가 기능구현

### Swagger 적용

### 사용자 인증 및 권한 관리

JWT를 이용한 사용자 인증

* [ ]  게시글 작성자만 수정 및 삭제 가능[](https://)
* [ ]  관리자는 모든 게시글 수정 및 삭제 가능

### 파일 업로드 기능

* [ ]  게시글에 이미지 첨부 기능 추가
* [ ]  이미지 파일 형식 크기 제한
* [ ]  이미지 업로드 시 S3와 같은 외부 스토리지 연동

### 댓글 기능

게시글에 댓글 추가 기능

* [ ]  댓글 작성, 수정, 삭제 (Soft Delete)
* [ ]  댓글 작성자는 본인의 댓글만 수정 및 삭제 가능

### 좋아요 및 조회수 기능

* [ ]  게시글 조회수 증가 기능
* [ ]  동일 사용자가 여러 번 조회 시 조회수 증가 방지

### 알림 기능

* [ ]  댓글 및 좋아요 시 알림 기능
* [ ]  수정 제한 경고 알림
