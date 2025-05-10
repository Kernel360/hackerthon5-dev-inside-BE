# 🧠 Dev-Inside - 개발자 커뮤니티 웹

 경험과 인사이트를 공유할 수 있는 글쓰기 중심 커뮤니티

## 🔗 배포 주소

미배포


## 🛠️ 기술 스택

### 프론트엔드
- React
- TypeScript

### 백엔드
- Java 17 / Spring Boot 3
- Spring Security + JWT 인증
- MySQL / JPA


## 📦 Project Service

### 게시글 
- 게시물 CRUD

### 인증 인가
- Spring Security + jwt
- 마이 페이지 조회/ 변경/ 회원탈퇴

### 댓글
- 댓글 CRUD

### 📂 프로젝트 구조
---
```text
├── 📂 hackerton5-dev-inside-BE/                               
│   ├── 📄 .gitignore                            
│   ├── 📄 README.md                             
│   ├── 📂 .ebextensions/                       
│   ├── 📂 src/                                
│   │   ├── 📂 main/                 
│   │   │   ├── 📂 java/                        
│   │   │   │   └── 📂 com/  
│   │   │   │       └── 📂 kerneldevinside/             
│   │   │   │           ├── 📂 controller/        
│   │   │   │           ├── 📂 service/          
│   │   │   │           ├── 📂 domain/             
│   │   │   │           ├── 📂 repository/        
│   │   │   │           └── 📂 dto/              
│   │   │   └── 📂 resources/                     
│   │   └── 📂 test/                             
│   ├── 📄 build.gradle                            
│   └── 📄 settings.gradle                      
```


## 👬 TeamMember
<table>
  <tr>
    <th>Backend</th>
    <th>Backend</th>
    <th>Backend</th>
  </tr>
  <tr>
    <td><img src="https://i.pinimg.com/736x/4b/74/01/4b7401e166d19975d18302042879e14e.jpg" width="100"/></td>
    <td><img src="https://i.pinimg.com/736x/4b/74/01/4b7401e166d19975d18302042879e14e.jpg" width="100"/></td>
    <td><img src="https://i.pinimg.com/736x/4b/74/01/4b7401e166d19975d18302042879e14e.jpg" width="100"/></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/ugiuk00">권동욱</a></td>
    <td align="center"><a href="https://github.com/yerim29">윤예림</a></td>
    <td align="center"><a href="https://github.com/JangBJ">장병중</a></td>
  </tr>
</table>


## 🌊 프로젝트 운영

### (1) Issue 관리
- GitHub `Issue`를 통해 작업과 문제를 관리합니다.
- `Issue`와 `Label`을 사용하여 작업 유형과 상태를 구분합니다.

### (2) Brach 생성 규칙
- 작업은 이슈 번호를 기반으로 브랜치를 생성하여 진행합니다.
- 브랜치는 `be/feat/issueNumber` 네이밍 규칙을 따릅니다.

### (3) Branch 전략
- Branch는 `main` - `dev` - `feature` 구조를 따릅니다.
- `main` : 운영 환경에서 사용되는 안정적인 코드만 포함합니다.
- `dev` : 새로운 기능이 병합되기 전 테스트 및 통합이 이루어지는 브랜치입니다.
- `feature` : 각 기능 개발을 위한 브랜치로, 작업이 완료되면 `dev` 브랜치로 병합됩니다.

```text
*   main
|   
|   *   dev
|   |   
|   |   *   feature/1
|   |   *   feature/2
|   |   *   feature/3
|   |  /
|   | /
|   *
|  /
| /
*   
```
