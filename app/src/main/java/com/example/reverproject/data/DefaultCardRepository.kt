package com.example.reverproject.data

import com.example.reverproject.data.source.local.CardDao
import com.example.reverproject.data.source.local.toExternal
import com.example.reverproject.data.source.local.toLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject


class DefaultCardRepository @Inject constructor(
    private val localDataSource: CardDao,
    private val ioDispatcher: CoroutineDispatcher
    ){

        fun observeAll() : Flow<List<Card>> {
            return localDataSource.observeAll().map { cards ->
                cards.toExternal()
            }
        }

        suspend fun createCard(title: String, text: String, progress: Int): String {
            val cardID = withContext(ioDispatcher){
                createCardID()
            }

            val card = Card(
                id = cardID,
                title = title,
                text = text,
                progress = progress,
            )
            localDataSource.upsert(card.toLocal())
            return cardID
        }

        private fun createCardID(): String{
            return UUID.randomUUID().toString()
        }

}
