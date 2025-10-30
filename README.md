

## Table of the development environment

<!-- シールド一覧 -->
<!-- 該当するプロジェクトの中から任意のものを選ぶ-->
<p style="display: inline">
  
  <!-- バックエンドの言語一覧 -->
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <!-- バックエンドのフレームワーク一覧 -->
</p>

## Table of contents

1. [Project](#Project)
2. [Environment](#Environment)
3. [Directory](#Directory)
4. [CrateDevelopmentEnvironment](#CreateDevelopmentEnvironment)

<!-- READMEの作成方法のドキュメントのリンク -->
<!-- <br />
<div align="right">
    <a href="READMEの作成方法のリンク"><strong>READMEの作成方法 »</strong></a>
</div>
<br />
<!-- Dockerfileのドキュメントのリンク -->
<!-- <div align="right">
    <a href="Dockerfileの詳細リンク"><strong>Dockerfileの詳細 »</strong></a>
</div>
<br /> 
<!-- プロジェクト名を記載 -->

## 8 Puzzle Problem Solver

<!-- プロジェクトについて -->

## Project

<p>
 My code is made of Java language and there are 3 files. <br/>
Each java file has each role such as PuzzleApplication for main method, Solutions for implementing A search*, and HeuristicConstraints for offering heuristics for A*. <br/>
In this project, an initial and goal state are defined as figure 1.<br/>
  
 <img width="451" height="56" alt="image" src="https://github.com/user-attachments/assets/7ec50759-10eb-4cf5-8c61-01d5a9ec52e0" /><br/>
Figure 1: intial state and goal state.<br/>
 
The process of finding the solution for 8 puzzle problem is shown with program code step by step as follows<br/>
1.	User runs program: <br/>
Command: java PuzzleApplication<br/>
↓<br/>
PuzzleApplication.main(String[] args)  // Initial point <br/>
-	Begins while loop <br/>
-	Calls setStrategy() method <br/>

2.	Menu to choose heuristic: <br/>
PuzzleApplication.setStrategy() method <br/>
-	Show users choics: <br/>
1: A* search (Misplaced tile) or 2: A* search (Manhattan) <br/>
-	User choose 1 or 2 <br/>

<img width="235" height="70" alt="image" src="https://github.com/user-attachments/assets/645fee5f-b8a2-4989-ab15-8b53ed5c303c" /> <br/>
Figure 2: The choice of heuristic types <br/>

↓<br/>
Starts:<br/>
// PuzzleApplication.java // define initial state and goal state.<br/>
goal = new Node(goalPuzzle) <br/>
initialNode = new Node (initialPuzzle)<br/>
↓<br/>
Relying on user selection:<br/>
// Both methods are in HeuristicConstraints<br/>
-	1 -> chooseHeuristics = AstarTileCalculator <br/>
-	2 -> chooseHeuristics = ManhattanCalculator <br/>
↓ <br/>
Calls: <br/>
Solutions.costSearch(initialNode, chosenHeuristics) // defined in Solutions.java <br/>

3.	A* Search Execution: <br/>
Solution.costSearch(Node root, HeurType heurType) <br/>
-	Starts: <br/>
openList = PriorityQueue<Node> // Nodes ordered by f = g+h <br/>
explored = Hashset<String> // closeList<br/>
root.heuristicCost = HeuristicConstraints.getCost()<br/>
root.totalCost = root.heuristicCost<br/>
openList.add(root)<br/>
-	Iteration loop:<br/>
while (openList not empty):<br/>
current = openlist.poll() // p/u lowest f node<br/>
if current.isGoalNode(goal): <br/>
printSolution(current) // goal state <br/>
return <br/>
explored.add(current.toString()) <br/>
current.expand() // defined in Node.java <br/>
-	Generates successors (makeMove) <br/>
-	Each successor.parentState = current <br/>
-	Successor.cost = tile moved <br/>
For each successor: <br/>
successor.heuristicCost = HeuristicConstraints.getCost() <br/>
successor.totalCost = current.totalCost + successor.cost + heuristicCost <br/>
openList.add(successor) <br/>

4.	Heuristic computation: <br/>
HeuristicConstraints (Node node, Node goal, HeurType type) // Constructor <br/>
-	If user inputs =1 -> misplacedTileCounter() <br/>
-	If user inputs =2 -> manhattanDistance() <br/>
-	Return h(n) to Solutions.costSearch <br/>

5.	Node expansion (successors) <br/>
Node.expand() <br/>
-	Searches for blank tile index <br/>
-	Calls makemove(Direction, state, blankIndex) <br/>
-	Confirm bounds <br/>
-	Swaps tiles <br/>
-	Generates successor Node <br/>
-	Adds to successors list <br/>

<img width="427" height="79" alt="image" src="https://github.com/user-attachments/assets/51af8ab3-f3c8-4520-8180-c91071707e7c" /> <br/>
Figure 3: Direction,node depth & cost, total cost, and heuristic estimation(misplaced tile) <br/>

6.	Solution arrived <br/>
Solutions.PrintResults() <br/>
-	Backtrack using parentState <br/>
-	Shows each Node: <br/>
-	Overall statistics <br/>
-	Iterations <br/>
-	Time it took (in ms) <br/>
-	Length <br/>
-	Total cost <br/>
-	Number of nodes dequeued <br/>
-	Space <br/>

<img width="413" height="102" alt="image" src="https://github.com/user-attachments/assets/561ed948-f743-4ccb-9867-d5e0d9baf638" /> <br/>
Figure 4: The results of A* search (misplaced tile) <br/>

7.	Go back to menu <br/>
PuzzleApplication.main() <br/>
-	Asks user: 0 or 1 <br/>
-	Loop or finish program <br/>

<img width="229" height="45" alt="image" src="https://github.com/user-attachments/assets/8b148d00-b0ad-4ffc-8d22-cfebfa78fd8a" /> <br/>
Figure 5: Choices of next steps for user. <br/>


</p>
<!-- プロジェクトの概要を記載 -->
<!-- 
  <p align="left">
    <br />
    プロジェクト詳細にBacklogのWikiのリンク 
    <a href="Backlogのwikiリンク"><strong>プロジェクト詳細 »</strong></a>
    <br />
    <br />　-->

<p align="right">(<a href="#top">Back to Top</a>)</p>
## Environment

<!-- 言語、フレームワーク、ミドルウェア、インフラの一覧とバージョンを記載 -->

| Language・Framework  | Version |
| --------------------- | ---------- |
| Java                  | 17.0.10    |
| Spring Boot           | 3.4.3      |
| Node.js               | 22.14.0    |
| React                 | 19.0.0     |

For other package version, please refer to pom.xml and package.json.

<p align="right">(<a href="#top">Back to Top</a>)</p>

## Directory

<!-- Treeコマンドを使ってディレクトリ構成を記載 -->

❯ tree -I 'node_modules'
```bash
.
├── email-writer-backend
│   ├── Dockerfile
│   ├── HELP.md
│   ├── README.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── email
│   │   │   │           └── emai
│   │   │   │               └── writer
│   │   │   │                   ├── EmailGeneratorController.java
│   │   │   │                   ├── EmailGeneratorService.java
│   │   │   │                   ├── EmailRequest.java
│   │   │   │                   └── EmailWriterApplication.java
│   │   │   └── resources
│   │   │       ├── application.properties
│   │   │       ├── static
│   │   │       └── templates
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── email
│   │                   └── emai
│   │                       └── writer
│   │                           └── EmailWriterApplicationTests.java
│   └── target
│       ├── classes
│       │   ├── application.properties
│       │   └── com
│       │       └── email
│       │           └── emai
│       │               └── writer
│       │                   ├── EmailGeneratorController.class
│       │                   ├── EmailGeneratorService.class
│       │                   ├── EmailRequest.class
│       │                   └── EmailWriterApplication.class
│       ├── email-writer-0.0.1-SNAPSHOT.jar
│       ├── email-writer-0.0.1-SNAPSHOT.jar.original
│       ├── generated-sources
│       │   └── annotations
│       ├── generated-test-sources
│       │   └── test-annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       ├── maven-status
│       │   └── maven-compiler-plugin
│       │       ├── compile
│       │       │   └── default-compile
│       │       │       ├── createdFiles.lst
│       │       │       └── inputFiles.lst
│       │       └── testCompile
│       │           └── default-testCompile
│       │               ├── createdFiles.lst
│       │               └── inputFiles.lst
│       └── test-classes
│           └── com
│               └── email
│                   └── emai
│                       └── writer
│                           └── EmailWriterApplicationTests.class
└── email-writer-frontend
    ├── README.md
    ├── email-writer-ext
    │   ├── content.css
    │   ├── content.js
    │   ├── hina_drawing.png
    │   └── manifest.json
    ├── eslint.config.js
    ├── index.html
    ├── package-lock.json
    ├── package.json
    ├── public
    │   └── vite.svg
    ├── src
    │   ├── App.css
    │   ├── App.jsx
    │   ├── assets
    │   │   └── react.svg
    │   ├── index.css
    │   └── main.jsx
    └── vite.config.js

```

<p align="right">(<a href="#top">Back to Top</a>)</p>

## CreateDevelopmentEnvironment


<!-- コンテナの作成方法、パッケージのインストール方法など、開発環境構築に必要な情報を記載 -->
<!-- 
### コンテナの作成と起動

.env ファイルを以下の環境変数例と[環境変数の一覧](#環境変数の一覧)を元に作成

.env
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=django-db
MYSQL_USER=django
MYSQL_PASSWORD=django
MYSQL_HOST=db
MYSQL_PORT=3306
SECRET_KEY=django
DJANGO_SETTINGS_MODULE=project.settings.local


.env ファイルを作成後、以下のコマンドで開発環境を構築 -->


### Check how it works

Check if you can access to https://verdant-sunburst-f21397.netlify.app/ <br/>
However, the server (backend) is made with render free plan, so you cannot get <br/>
a reply because the sever has a limited time. <br/>
Please see the following picture as an example. <br/>

Ex.1 Reply with professional tone. <br/>
<img width="893" alt="image" src="https://github.com/user-attachments/assets/9dac86c0-6b0a-4c99-a8c5-6c02102928ec" />

Ex.2 Reply with casual tone. <br/>
<img width="893" alt="image" src="https://github.com/user-attachments/assets/2d73b291-7fa0-4da0-b3c8-b4b0aa90fc19" />

Ex.3 Reply with friendly tone. <br/>
<img width="877" alt="image" src="https://github.com/user-attachments/assets/f18aef02-75c0-4c51-ade2-cd025dce72ad" />


<!-- 
<### コンテナの停止

以下のコマンドでコンテナを停止することができます

make down

### 環境変数の一覧

| 変数名                 | 役割                                      | デフォルト値                       | DEV 環境での値                           |
| ---------------------- | ----------------------------------------- | ---------------------------------- | ---------------------------------------- |
| MYSQL_ROOT_PASSWORD    | MySQL のルートパスワード（Docker で使用） | root                               |                                          |
| MYSQL_DATABASE         | MySQL のデータベース名（Docker で使用）   | django-db                          |                                          |
| MYSQL_USER             | MySQL のユーザ名（Docker で使用）         | django                             |                                          |
| MYSQL_PASSWORD         | MySQL のパスワード（Docker で使用）       | django                             |                                          |
| MYSQL_HOST             | MySQL のホスト名（Docker で使用）         | db                                 |                                          |
| MYSQL_PORT             | MySQL のポート番号（Docker で使用）       | 3306                               |                                          |
| SECRET_KEY             | Django のシークレットキー                 | secretkey                          | 他者に推測されないランダムな値にすること |
| ALLOWED_HOSTS          | リクエストを許可するホスト名              | localhost 127.0.0.1 [::1] back web | フロントのホスト名                       |
| DEBUG                  | デバッグモードの切り替え                  | True                               | False                                    |
| TRUSTED_ORIGINS        | CORS で許可するオリジン                   | http://localhost                   |                                          |
| DJANGO_SETTINGS_MODULE | Django アプリケーションの設定モジュール   | project.settings.local             | project.settings.dev                     |

### コマンド一覧

| Make                | 実行する処理                                                            | 元のコマンド                                                                               |
| ------------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| make prepare        | node_modules のインストール、イメージのビルド、コンテナの起動を順に行う | docker-compose run --rm front npm install<br>docker-compose up -d --build                  |
| make up             | コンテナの起動                                                          | docker-compose up -d                                                                       |
| make build          | イメージのビルド                                                        | docker-compose build                                                                       |
| make down           | コンテナの停止                                                          | docker-compose down                                                                        |
| make loaddata       | テストデータの投入                                                      | docker-compose exec app poetry run python manage.py loaddata crm.json                      |
| make makemigrations | マイグレーションファイルの作成                                          | docker-compose exec app poetry run python manage.py makemigrations                         |
| make migrate        | マイグレーションを行う                                                  | docker-compose exec app poetry run python manage.py migrate                                |
| make show_urls      | エンドポイントをターミナル上で一覧表示                                  | docker-compose exec app poetry run python manage.py show_urls                              |
| make shell          | テストデータの投入                                                      | docker-compose exec app poetry run python manage.py debugsqlshell                          |
| make superuser      | スーパーユーザの作成                                                    | docker-compose exec app poetry run python manage.py createsuperuser                        |
| make test           | テストを実行                                                            | docker-compose exec app poetry run pytest                                                  |
| make test-cov       | カバレッジを表示させた上でテストを実行                                  | docker-compose exec app poetry run pytest --cov                                            |
| make format         | black と isort を使ってコードを整形                                     | docker-compose exec app poetry run black . <br> docker-compose exec app poetry run isort . |
| make update         | Poetry 内のパッケージの更新                                             | docker-compose exec app poetry update                                                      |
| make app            | アプリケーション のコンテナへ入る                                       | docker exec -it app bash                                                                   |
| make db             | データベースのコンテナへ入る                                            | docker exec -it db bash                                                                    |
| make pdoc           | pdoc ドキュメントの作成                                                 | docker-compose exec app env CI_MAKING_DOCS=1 poetry run pdoc -o docs application           |
| make init           | Terraform の初期化                                                      | docker-compose -f infra/docker-compose.yml run --rm terraform init                         |
| make fmt            | Terraform の設定ファイルをフォーマット                                  | docker-compose -f infra/docker-compose.yml run --rm terraform fmt                          |
| make validate       | Terraform の構成ファイルが正常であることを確認                          | docker-compose -f infra/docker-compose.yml run --rm terraform validate                     |
| make show           | 現在のリソースの状態を参照                                              | docker-compose -f infra/docker-compose.yml run --rm terraform show                         |
| make apply          | Terraform の内容を適用                                                  | docker-compose -f infra/docker-compose.yml run --rm terraform apply                        |
| make destroy        | Terraform で構成されたリソースを削除                                    | docker-compose -f infra/docker-compose.yml run --rm terraform destroy                      |

### リモートデバッグの方法

リモートデバッグ を使用する際は以下の url を参考に設定してください<br>
[Django のコンテナへリモートデバッグしよう！](https://qiita.com/shun198/items/9e4fcb4479385217c323)

## Troubleshooting

### .env: no such file or directory

.env ファイルがないので環境変数の一覧を参考に作成しましょう

### docker daemon is not running

Docker Desktop が起動できていないので起動させましょう

### Ports are not available: address already in use

別のコンテナもしくはローカル上ですでに使っているポートがある可能性があります
<br>
下記記事を参考にしてください
<br>
[コンテナ起動時に Ports are not available: address already in use が出た時の対処法について](https://qiita.com/shun198/items/ab6eca4bbe4d065abb8f)

### Module not found

make build

を実行して Docker image を更新してください-->

<p align="right">(<a href="#top">Back to Top</a>)</p>
