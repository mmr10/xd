package eu.els.sie.xmlfirst.batch.reader;

import eu.els.sie.xmlfirst.batch.config.BatchProperties;
import eu.els.sie.xmlfirst.ecm.core.domain.models.mongo.EditorialElement;
import eu.els.sie.xmlfirst.ecm.core.domain.repository.mongo.EditorialElementRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmarzougui on 13/02/2017.
 */
@Component
public class MongoItemReader extends RepositoryItemReader<EditorialElement> {


    @Inject
    private EditorialElementRepository editorialElementRepository;

    @Inject
    BatchProperties batchProperties;

    @PostConstruct
    public void init() {
        setMethodName("findByType");
        setPageSize(batchProperties.getPageSize());
        setRepository(editorialElementRepository);
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("dateUpdate", Sort.Direction.ASC);
        setSort(sorts);
    }
}
