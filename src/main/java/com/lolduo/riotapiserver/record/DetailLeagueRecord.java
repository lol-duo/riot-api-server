package com.lolduo.riotapiserver.record;

public record DetailLeagueRecord(
        DetailLeagueRecordEntry[] entries
) {

}

record DetailLeagueRecordEntry(
        String summonerId
) {

}