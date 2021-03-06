package com.ak47.cms.cms.task

import com.ak47.cms.cms.api.PBCCrawler
import com.ak47.cms.cms.dao.PBCArticalRepository
import com.ak47.cms.cms.enums.PBCType
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PbcArticalTask {

    val log = LoggerFactory.getLogger(PbcArticalTask::class.java)

    @Autowired lateinit var PBCArticalRepository: PBCArticalRepository
    var newsArticleCrawer: PBCCrawler = PBCCrawler.instanceCrawler()

    fun doSyncPbcNewsArticleDta() {

        val pageTotal = newsArticleCrawer.getPageNoSum(PBCType.NEWS.url)
        for (pageNo in 1..pageTotal+1) {
            println(pageNo)
            val articalList = newsArticleCrawer.getPageNewsArticle(pageNo,PBCType.NEWS.typeCode)
            articalList.forEach {
                val url = it.url
                var count = 0
                count = PBCArticalRepository.countByUrl(url!!)
                if (count == 0) {
                    PBCArticalRepository.save(it)
                }
            }
        }
    }

}