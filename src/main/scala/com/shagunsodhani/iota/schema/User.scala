package com.shagunsodhani.iota.schema

case class User (Id: Long, 
    Reputation: Long,
    DisplayName: String,
    Views: Long,
    UpVotes: Long,
    DownVotes: Long,
    AccountId: Long) {
}