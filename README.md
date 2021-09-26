# kdt-live-session-repo
> kdt 데브코스 8주차 개인 프로젝트 

* SpringBoot 클론 코딩
    * 주문 관리 프로젝트
    * 상품 페이지에서 상품을 등록할 수 있음
    * 주문 페이지에서 고객이 상품을 주문할 수 있음
* 관리자 페이지 - Thymeleaf와 Bootstrap으로 구현
    * 상품의 이름, 카테고리, 가격, 설명을 입력받아서 상품을 등록할 수 있다.
    * 등록된 상품을 목록에 조회할 수 있다.
* 상품 주문 페이지 - React로 구현
    * 원하는 상품을 추가하고 이메일, 주소, 우편번호를 넣은 후 결제할 수 있다.

## 구현 상황
- 상품
    - [x] 상품 등록
    - [x] 상품 목록 조회
    - [ ] 상품 수정
    - [ ] 상품 삭제
- 주문
    - [x] 주문 등록
    - [ ] 주문 목록 조회
    - [ ] 주문 삭제

<br>

# 🛠 기술 스택

## BackEnd
* Java 16
* Spring Boot
* Spring Data JDBC
* Thymeleaf
* Bootstrap

## FrontEnd
* React

## Data
* H2
* MySQL

<br>

# 📢 컨벤션
## Commit
>  Reference: http://karma-runner.github.io/1.0/dev/git-commit-msg.html

| Type | Contents |
|--|--|
|feat| new feature for the user, not a new feature for build script
|fix| bug fix for the user, not a fix to a build script
|docs| changes to the documentation
|refactor| refactoring production code, eg. renaming a variable
|style| formatting, missing semi colons, etc; no production code change
|test| adding missing tests, refactoring tests; no production code change)
|chore| updating grunt tasks etc; no production code change

- Example

    ```
    refactor: Refactor subsystem X for readability 

    {body...}

    Issue #1 or Resolves #1 // reference issues
    ```