package com.shagunsodhani.iota.schema

case class Question (Id: Long, 
    AcceptedAnswerId: Long,
    CreationDate: String,
    Score: Long,
    ViewCount: Long,
    Body: String,
    OwnerUserId: Long,
    LastEditorUserId: Long,
    LastEditorDisplayName: String,
    LastEditDate: String,
    LastActivityDate: String,
    CommunityOwnedDate: String,
    ClosedDate: String,
    Title: String,
    Tags: String,
    AnswerCount: Long,
    CommentCount: Long,
    FavoriteCount: Long){
}