# Twitter4jTutorial

Twitter4jのチュートリアルプログラム

## Description

全公開ツイートの1%を抽出する

CSVファイルも作成可能

## Requirement

twitter4j: http://twitter4j.org/ja/index.html

twitter開発者アカウント

## CreateAccount

*Twitterアカウントを作成
*携帯電話番号を登録して認証
*https://apps.twitter.com/ にアクセスしてCreate a new applicationをクリック
*[Name]、[Description]、[Website]を適当に入力
*[Key and Access Tokens]タブの[Consumer key] と [Consumer secret] をメモ
*[Create my access token] をクリックし[Access token] と [Access token secret] をメモ


## Usage

srcフォルダ直下に以下の内容のtwitter4j.propertiesを作成

    debug=true
    oauth.consumerKey=****************
    oauth.consumerSecret=****************
    oauth.accessToken=****************
    oauth.accessTokenSecret=****************

twitter4jライブラリを入れてプログラムを起動


