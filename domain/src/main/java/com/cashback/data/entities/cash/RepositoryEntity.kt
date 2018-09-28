package com.cashback.data.entities.cash

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.security.acl.Owner

@Entity(tableName = "repositories")
data class RepositoryEntity(
        @PrimaryKey
        val id: Int,
        val nodeId: String,
        val name: String,
        val fullName: String,
        @Embedded(prefix = "owner")
        val owner: Owner,
        val private: Boolean,
        val htmlUrl: String,
        val description: String,
        val fork: Boolean,
        val url: String,
        val archiveUrl: String,
        val assigneesUrl: String,
        val blobsUrl: String,
        val branchesUrl: String,
        val collaboratorsUrl: String,
        val commentsUrl: String,
        val commitsUrl: String,
        val compareUrl: String,
        val contentsUrl: String,
        val contributorsUrl: String,
        val deploymentsUrl: String,
        val downloadsUrl: String,
        val eventsUrl: String,
        val forksUrl: String,
        val gitCommitsUrl: String,
        val gitRefsUrl: String,
        val gitTagsUrl: String,
        val gitUrl: String,
        val issueCommentUrl: String,
        val issueEventsUrl: String,
        val issuesUrl: String,
        val keysUrl: String,
        val labelsUrl: String,
        val languagesUrl: String,
        val mergesUrl: String,
        val milestonesUrl: String,
        val notificationsUrl: String,
        val pullsUrl: String,
        val releasesUrl: String,
        val sshUrl: String,
        val stargazersUrl: String,
        val statusesUrl: String,
        val subscribersUrl: String,
        val subscriptionUrl: String,
        val tagsUrl: String,
        val teamsUrl: String,
        val treesUrl: String
)