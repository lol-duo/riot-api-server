package com.lolduo.riotapiserver.record;

public record MatchRecord(
        MatchInfoRecord info
) {
}

record MatchInfoRecord(
        long gameCreation,
        int gameDuration,
        long gameEndTimestamp,
        long gameId,
        String gameMode,
        String gameName,
        long gameStartTimestamp,
        String gameType,
        String gameVersion,
        int mapId,
        ParticipantRecord[] participants,
        String platformId,
        int queueId
) {

}

record ParticipantRecord(
        String individualPosition,
        int item0,
        int item1,
        int item2,
        int item3,
        int item4,
        int item5,
        int item6,
        String lane,
        PerksRecord perks,
        String puuid,
        String role,
        String summonerId,
        String summonerName,
        int teamId,
        String teamPosition,
        boolean win
) {

}

record PerksRecord(
        StatPerksRecord statPerks,
        StyleRecord[] styles
) {

}

record StatPerksRecord(
        int defense,
        int flex,
        int offense
) {

}

record StyleRecord(
        String description,
        SelectionRecord[] selections,
        int style
) {

}

record SelectionRecord(
        int perk,
        int var1,
        int var2,
        int var3
) {

}