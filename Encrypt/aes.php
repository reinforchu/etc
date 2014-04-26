<?php

/*

情報セキュリティ 課題５
「PHPによるAES暗号化/復号化」

テスト用として、ルートにimg.jpgというファイルがあります。
期待される結果は、img.jpg, enc.txt, dec.txtのファイルサイズが同一で、
enc.txt以外はハッシュ値が同一である。

テスト用ファイルとパスフレーズはユーザが任意でフォームから送信されたものとして仮定しています。
暗号化の際、パスフレーズに+saltを付加し、辞書攻撃・ブルートフォースアタック対策しています。
saltは開発者が任意で設定できるものとします。

$filepathを変更し、実行形式ファイルなどでも暗号化/復号化可能ですが、
ファイル拡張子とHTTPヘッダも同時に変更する必要性があります。
また、パーミッション設定をCGIが書き込めるようにする必要性もあります。

*/


/* テスト用ファイル */
$filepath = "img.jpg"; // 暗号化対象ファイル
$handle = fopen($filepath, "rb"); // バイナリで読み込む
$fileBin = fread($handle, filesize($filepath)); // 変数はバイナリ
fclose($handle);

/* テスト用パスフレーズ */
$passphrase = "windows";
$salt = "apple";

/* main */

/* AES暗号化モジュール初期化及びAES暗号化 */
$aesModule = mcrypt_module_open('rijndael-256', '', 'ofb', ''); // AES暗号化モジュールを開始する
$aesIV = mcrypt_create_iv(mcrypt_enc_get_iv_size($aesModule), MCRYPT_DEV_RANDOM); // 暗号化モジュール用の任意のベクトル値
$aesKeySize = mcrypt_enc_get_key_size($aesModule); // AES暗号化鍵長
$key = substr(sha1($passphrase.$salt), 0, $aesKeySize); // 復号鍵の作成
mcrypt_generic_init($aesModule, $key, $aesIV); // 暗号化の初期化
$encrypted = mcrypt_generic($aesModule, $fileBin); // AES暗号化
mcrypt_generic_deinit($aesModule); // 暗号化のハンドラの終了

/* テスト用ファイルダンプ */
$handle = fopen("enc.txt", "w+"); // AES暗号ファイル
fwrite($handle, $encrypted);
fclose($handle);
$filepath = "enc.txt"; // 復号化対象ファイル
$handle = fopen($filepath, "rb");
$encFileBin = fread($handle, filesize($filepath));
fclose($handle);


/* AES復号化 */
mcrypt_generic_init($aesModule, $key, $aesIV); // AES復号化の初期化
$decrypted = mdecrypt_generic($aesModule, $encFileBin); // AES復号化
mcrypt_generic_deinit($aesModule); // 暗号化のハンドラの終了
mcrypt_module_close($aesModule); // AES暗号化モジュールの終了

/* デバッグ用ファイルダンプ */
$handle = fopen("dec.txt", "w+"); // AES復号ファイル
fwrite($handle, $decrypted);
fclose($handle);

/* 復号化ファイル出力 */
if (!empty($decrypted)) { // 復号に失敗した場合は改行コードが返る
	header('Content-type: image/jpeg');
	header('Content-Disposition: attachment; filename="aes.jpg"');
	readfile("dec.txt");
} else {
	print("復号化失敗");
}