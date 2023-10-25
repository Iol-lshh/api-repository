# api-repository
api를 재귀적으로 등록 및 제공하는 마이크로 서비스

# Entity
## api
- [Resourcer](#resourcer)
    - 데이터 제공자
- [Router](#router)
    - 요청 경로
- [Query](#query)
    - 데이터 요청 형식
- [QueryParameter](#queryparameter)
    - Query 파라미터 형식
## 요청
- [ResourseRequest](#resourserequest)
    - 요청
- [QueryArgument](#queryargument)
    - 요청에 기록하는 Query 요청 인자

## 권한
- [Requester](#requester)
    - 요청자
- [Role](#role)
    - 인가 권한 묶음
- [AuthorizedByRole](#authorizedbyrole)
    - Role:Authorized = n:m
- [AuthorizedRouter](#authorizedrouter)
    - 요청자의 인가된 router
    - type: AR

## Entity 세부 사항
### 공통 상속
- created
    - 생성 일자
- deleted
    - 삭제 일자
- is_enabled
    - 활동 여부

### _History (히스토리 Entity)
- 생성/수정시 작성 (트리거)
- registed
    - 히스토리 기록 일자
- detail
    - 히스토리 추가 사항
### Resourcer
- id
- name
- path
- description
- key

### Router
- id
- name
- path
- description
- query_id

### Query
- id
- resourcer_id
- name
- contents
- descrpition

### QueryParameter
- id
- query_id
- name
- description
- type
- is_optional

### ResourseRequest
- id
- requester_id
- router_id
- state
    - WAIT, OK, FAIL

### QueryArgument
- id
- query_parameter_id
- request_history_id
- contents

### Requester
- id
- name

### AuthorizedRouter
- id
- router_id
    - AuthorizedByRole 로 중복 가능
- requester_id

### Role
- id
- name
- description
- type
    - AR: AuthorizedRouter

### AuthorizedByRole
- id
- role_id
- type
- contents_id


---

# Service

## Agent
### 요청 처리
1. 라우팅 처리
2. 요청 생성: WAIT
3. 쿼리 처리
4. 요청 상태 변경: OK
5. 데이터 응답

### 라우팅 처리
1. 라우팅 찾기
2. 라우팅 인가 확인

### 쿼리 처리
1. 쿼리 찾기
2. 쿼리 파라미터 찾기
3. 쿼리 요청


## Router
### find
- 리스트
- 단일
### save
- 생성
- 수정

## Query
### find
- 리스트
- 단일
### save
- 생성
- 수정

## Resoucer
### find
- 리스트
- 단일

### save
- 생성
- 수정


<http://localhost:8080/swagger-ui/index.html>


# 작업 순서
1. 어드민 (a: 23.10.25)
2. 동적 라우팅
3. 인증/인가
