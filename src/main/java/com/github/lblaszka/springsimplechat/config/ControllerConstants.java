package com.github.lblaszka.springsimplechat.config;

public class ControllerConstants
{
    public static final String CHAT_ROOM_URL_ID = "idChatRoom";
    public static final String MESSAGE_URL_ID = "idMessage";
    public static final String PARTICIPATION_URL_ID = "idParticipation";

    public static final String MAPPING_CHAT_ROOM_URL_ID = "{"+CHAT_ROOM_URL_ID+"}";
    public static final String MAPPING_MESSAGE_URL_ID = "{"+MESSAGE_URL_ID+"}";
    public static final String MAPPING_PARTICISTRING_URL_ID = "{"+PARTICIPATION_URL_ID + "}";

    public static final String CHAT_ROOM_URL = "chat-rooms";
    public static final String PARTICIPATION_URL = CHAT_ROOM_URL+"/{"+CHAT_ROOM_URL_ID+"}/"+"participants";
    public static final String MESSAGE_URL = CHAT_ROOM_URL+"/{"+CHAT_ROOM_URL_ID+"}/"+"messages";
}
