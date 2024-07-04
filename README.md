# JAVA-Course-Design-Maze-Game

## 构建环境

+ `JDK` : 21.0.2 2024-01-16 LTS

+ `Compiler` : IntelliJ IDEA 2024.1.4

## 文件说明

### 简单框架

```
└─game
    ├─res···············资源文件夹
    │  ├─image          存放图片(png)
    │  │  ├─map         地图图片
    │  │  └─player      玩家图片
    │  └─maps           存放地图(txt)
    └─src···············源码文件夹
        ├─Entity        实体包
        ├─main          主包
        └─tile          地图管理包
```

### 详细说明

```
└─game
    ├─res
    │  ├─image
    │  │  ├─map
    │  │  │      image.png(...)
    │  │  │
    │  │  └─player
    │  │          image.png(...)
    │  │
    │  └─maps
    │          map_16.txt(...)
    │
    └─src
        ├─Entity
        │      Entity.java
        │      _Player.java
        │
        ├─main
        │      Check.java
        │      main.java
        │      _Handler.java
        │      _Panel.java
        │
        └─tile
                Tile.java
                TileManager.java
```

## 更新日志

+ `v0.9.0` : 完成游戏主题内容(invisible : 由于错误的`git push --force`而丢失orz)

+ `v1.0.0` : 补充了简单的游戏难度选择和结束游戏界面,修复了`v0.9.0`中只能在集成编译环境下构建的问题(released)

+ `v1.1.0` : \#TODO : 更完善美观的界面,添加设置优化选项的功能

+ `v2.0.0` : \#TODO : 随机生成地图等...
