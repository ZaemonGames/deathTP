# deathTP
死んだ場所にテレポートするコマンドです。

## Usage
死んだら/deathを実行してテレポートするためのGUIを開きます。
紙に標準を合わせると、死んだ場所とコストが表示されます。
エメラルドを押すとコストを支払い、テレポートします。

コストは、死んだ場所からプレイヤーが現在いる位置までの直線距離 x config.ymlに設定したmultiplierです。

## Prerequisite Plugin
- [Vault](https://github.com/milkbowl/Vault)

## Command
| command | usage  | detail                     |
|---------|--------|----------------------------|
| /death  | /death | 死んだ場所にてレポートするためのGUIを表示します。 |

## Config
| config                       | type         | detail                                   |
|------------------------------|--------------|------------------------------------------|
| multiplier                   | int          | 距離にかける数                                  |
| limitPerLocation             | int          | 同じ場所にテレポートできる回数                          |
| noPermissionMessage          | stringList   | 権限がない場合にプレイヤーに送信するメッセージ                  |                  |
| deathMessage                 | stringList   | 死んだ時にプレイヤーに送信するメッセージ                     |
| deathLocationNotFoundMessage | stringList   | 死んだ場所が見つからない時にプレイヤーに送信するメッセージ            |
| differentWorldMessage        | stringList   | 死んだワールドとプレイヤーがいるワールドが違う時にプレイヤーに送信するメッセージ |
| notEnoughBalanceMessage      | stringList   | テレポートするためのコストが足りない時にプレイヤーに送信するメッセージ      |
| teleportedMessage            | stringList   | テレポートした時にプレイヤーに送信するメッセージ                 |
| worldName                    | array-string | ワールドの表示名を変更する                            |

## Permissions
| permissionNode   | default | detail              |
|------------------|---------|---------------------|
| deathtp.teleport | true    | テレポートするための権限        |
| deathtp.free     | false   | コストを支払わずにテレポートできる権限 |

## Framework
- [inventory-framework](https://github.com/DevNatan/inventory-framework)