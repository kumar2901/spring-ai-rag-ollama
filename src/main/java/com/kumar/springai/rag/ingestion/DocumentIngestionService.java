package com.kumar.springai.rag.ingestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

@Service
public class DocumentIngestionService implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(DocumentIngestionService.class);
    private final VectorStore vectorStore;

    @Value("classpath:/pdf/spring-boot-reference.pdf")
    private  Resource resource;

    DocumentIngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        TikaDocumentReader reader = new TikaDocumentReader(resource);
        TextSplitter textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));
        log.info("Completed Ingesting PDF file");
    }
}
