package eu.els.sie.xmlfirst.batch.writer;

import eu.els.sie.xmlfirst.ecm.core.domain.models.mongo.EditorialElement;
import eu.els.sie.xmlfirst.ecm.core.services.indexation.IIndexationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mmarzougui on 13/02/2017.
 */
@Component
public class SolrItemWriter implements ItemWriter<EditorialElement> {

    private Logger logger = LoggerFactory.getLogger(SolrItemWriter.class);

    @Resource
    private IIndexationService indexationService;

    @Override
    public void write(final List<? extends EditorialElement> items) throws Exception {

            for(EditorialElement item:items){
                logger.debug("Indexing flashId: '{}' ...", item.getFlashId());
                indexationService.updateEditorialEntityIndex(item.getFlashId());
                logger.debug("flashId: '{}' is indexed", item.getFlashId());
            }


    }
}
