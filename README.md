# SMP-Android
창업 (Start up) 교육 관리 (Management) 프로그램 (Program) for KHU

> 경희대학교 SW GURU 프로젝트 그룹에서 진행되었습니다.

<br>

## 1. 프로젝트의 목표 및 내용
창업 교육을 할 때 멘토가 멘티(창업팀)들의 교육 및 작업 과정을 효율적으로 관리할 수 있도록 Android Application을 개발합니다. WBS와 공지사항이 주요 기능이고, 자세한 기능은 2번에서 확인 바랍니다.  
<image src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3db97e0d-16a1-489a-9ad3-d6186b9586d1/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20201218%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20201218T185849Z&X-Amz-Expires=86400&X-Amz-Signature=ff721940c2327e2e635a436ad622178a0bf734eafde3f7aae6d57e7a47140390&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" width="500">  
- 멘티는 하나의 팀을 갖고 있다. (멘티:팀=N:1)
- 멘토는 여러개의 팀을 관리하고, 팀별 멘토는 여러명일 수 있다. (팀:멘토 = N:N)

<br><br>

## 2. SW 기능 및 UI
- 협업툴 : Git, GitHub, Notion
- Client : Android Studio, JAVA
- Server : KINX IX Cloud(Ubuntu 14.04), MySQL, PHP

### 1) 멘티와 멘토 회원가입을 나눠 구현 및 서버연동
<image src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6de45ba5-e6e5-4b4d-80c5-be25b57a49c4/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20201218%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20201218T190321Z&X-Amz-Expires=86400&X-Amz-Signature=3b82c86b2adb58d1dfc1cdff6af52e8e2afce982489c16b77cfacb045f34a450&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" width="600">
중복확인 : 이메일이 이미 회원가입된 아이디라면 사용할 수 없다.

### 2) 멘티 로그인 및 메인화면
<image src="https://user-images.githubusercontent.com/37680108/102651159-64228c00-41af-11eb-8299-9c50987ff550.png" width="600">

### 3) 멘토 로그인 및 메인화면
<image src="https://user-images.githubusercontent.com/37680108/102651215-81575a80-41af-11eb-932b-1de99961088a.png" width="600">

### 4) 멘토 팀만들기 기능
<image src="https://user-images.githubusercontent.com/37680108/102651274-9f24bf80-41af-11eb-8e40-91d245a0f1d4.png" width="500">

### 5)WBS
#### 팀별 목록 및 세부사항 확인 : 칸반보드 형태로 구현 (예정, 진행, 완료)
<image src="https://user-images.githubusercontent.com/37680108/102651476-f75bc180-41af-11eb-9b74-acd7b5826275.png" width="600">

#### 할 일 (프로젝트 작업) 추가 :
<image src="https://user-images.githubusercontent.com/37680108/102651491-fd51a280-41af-11eb-9109-5c251e1e6ff7.png" width="600">

<br><br>


## 3. DB 구조
![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3edeacc6-9c5f-4e96-a0e1-beb27e83fe5e/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20201218%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20201218T191036Z&X-Amz-Expires=86400&X-Amz-Signature=817e78f8dff70c551a1bff50f65b028436fc0feca2e7ef9063da346a9d55ce45&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)
