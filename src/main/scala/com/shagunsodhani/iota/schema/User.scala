package com.shagunsodhani.iota.schema

case class User (Id: Long, 
    Reputation: Long,
    CreationDate: String,
    DisplayName: String,
    EmailHash: String,
    lastAccessDate: String,
    WebsiteUrl: String,
    Location: String,
    Age: Long,
    AboutMe: String,
    Views: Long,
    UpVotes: Long,
    DownVotes: Long,
    AccountId: Long) {
}