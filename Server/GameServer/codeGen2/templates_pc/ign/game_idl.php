<?php
require_once("XByteArray.php");
/**
	Auto generated by xproto.exe
@author
	Dany
*/

  interface _EINTERNAL_NOTIFY_BY_PROXY_A
	{
	
		const CLIENT_DISCONNECT =1;
		const CLIENT_NOT_EXIST_WHEN_RESPONSE =2;
		const CLIENT_WRITE_ERROR =3;
		const CLIENT_SOCKET_DEAD_ALREADY =4;

	}
  interface _ECOMMON_SERVER_ERROR_A
	{
	
		const SUCCESS =0;
		const PROXY_FASTCGI_INTERAL_ERROR =1;
		const PROXY_SENDTO_FCGI_FAILED_TOO_MUCH =2;
		const PROXY_FCGI_REQUEST_TIMEOUT =3;
		const PROXY_FCGI_UNAVAILABLE =4;
		const KICKED_OUT_BY_OTHER_DUPLICATED_LOGIN =5;
		const KICKED_OUT_FOR_IDLE_TOO_LONG =6;
		const KICKED_OUT_FOR_BAD_STATE_NOT_LOGIN_YET =7;
		const KICKED_OUT_FOR_BAD_STATE_LOING_ALREADY =8;
		const SERVER_UNDER_MAINTAIN =9;

	}
  interface _EMSG_ServerInterface
	{
	
		const CMSG_DoLogin =1;
		const CMSG_SendInternalNotifyByProxy =12;
		const CMSG_OnKickout =25;
		const CMSG_SendPing =34;
		const CMSG_SendProtoBuff =35;
		const CMSG_MAX =36;

	}
  interface _EMSG_ServerEvent
	{
	
		const SMSG_OnServerErrorMessage =1;
		const SMSG_OnSendZipData =2;
		const SMSG_OnPong =46;
		const SMSG_OnProtoReponse =47;
		const SMSG_MAX =48;

	}
