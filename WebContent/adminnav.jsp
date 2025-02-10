<!--

<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--
<title>${param.title}</title>
 -->


<style>
    /* ボディのスタイル設定 */
    body {
        margin: 0; /* ページの余白をなくす */
        padding: 0; /* ページの内側の余白をなくす */
        height: 100vh; /* 画面の高さを100%に設定 */
    }

    /* ナビゲーションバーのスタイル設定 */
    .nav {
        border-radius: 8px; /* 角を丸くする */
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* 影を軽く追加 */
        justify-content: center; /* アイテムを中央に配置 */
    }

    /* ナビゲーションアイコンの配置スタイル */
    .hako {
        display: flex; /* フレックスボックスでレイアウト */
        justify-content: space-around; /* アイコンを均等に配置 */
        align-items: center; /* アイコンを縦中央に配置 */
        width: 100%; /* 横幅を100%に設定 */
        padding: 5px 0;
    }

    /* リンク（アイコン）のスタイル設定 */
    .hako a {
        font-size: 1rem; /* アイコンのフォントサイズを大きく設定 */
        text-decoration: none; /* リンクの下線を消す */
        color: #333; /* アイコンの色を濃いグレーに設定 */
        padding: 10px 20px; /* アイコン周りの内側の余白を設定 */
        border: 2px solid #d3d3d3; /* 枠線を薄いグレーに設定 */
        border-radius: 8px; /* 角を丸くする */
        background-color: #f9f9f9; /* 背景色を薄いグレーに設定 */
        transition: transform 0.3s, color 0.3s, background-color 0.3s; /* ホバー時にスムーズな変化を追加 */
    }

    /* リンク（アイコン）ホバー時のスタイル設定 */
    .hako a:hover {
        transform: scale(1.2); /* ホバー時にアイコンを拡大 */
        color: #007bff; /* ホバー時の色を青に設定 */
        background-color: #e0e0e0; /* ホバー時の背景色をさらに明るいグレーに設定 */
        border-color: #007bff; /* ホバー時の枠線の色を青に設定 */
    }
</style>
</head>
<body>
<!-- ナビゲーションバー -->
<div class="nav">
    <!-- アイコンを横並びにするためのコンテナ -->
    <div class="hako">
        <!-- 戻るボタン -->
        <a href="javascript:void(0)" onclick="history.back()">🔙</a>
        <!-- ログインページへのリンク -->

                <a href="/meal-mate/admin/logout">Logout</a>
        <!-- ホームページへのリンク -->
        <a href="/meal-mate/admin/top">🏠</a>
    </div>
</div>



</body>
</html>
