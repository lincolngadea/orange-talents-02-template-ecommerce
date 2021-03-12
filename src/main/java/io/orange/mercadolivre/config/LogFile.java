package io.orange.mercadolivre.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFile {

    private final Logger logger = LoggerFactory.getLogger((LogFile.class));

    public void log(){
        logger.info("Log de informação");
        logger.warn("log de aviso, algo está errado ou faltando! Cuidado!");
        logger.error("Log de erro, algo de errado aconteceu!");
        logger.debug("Log de depuração, contém informações");
        logger.trace("Log de rastreabilidade, contém informações mais ref");
    }
}
