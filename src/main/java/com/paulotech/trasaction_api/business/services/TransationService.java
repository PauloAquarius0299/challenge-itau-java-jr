package com.paulotech.trasaction_api.business.services;

import com.paulotech.trasaction_api.controllers.dtos.TransacaoRequestDTO;
import com.paulotech.trasaction_api.infra.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransationService {
    private final List<TransacaoRequestDTO> listaTransacao = new ArrayList<>();

    public void addTransacao(TransacaoRequestDTO dto) {
        log.info("Iniciando processos de gravar transações" + dto);
        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.info("Data e hora maiores do que a atual");
            throw new UnprocessableEntity("Data e hora mais do que a atual");
        }
        if(dto.value() < 0){
            log.info("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        log.info("Transações add com sucesso");

        listaTransacao.add(dto);
    }

    public void limparTransacoes() {
        log.info("Iniciar processamento para deletar transações");
        listaTransacao.clear();
        log.info("Transações deletados com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBuscar){
        log.info("Iniciadas transações por tempo" + intervaloBuscar);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBuscar);

        log.info("Retornando transações com sucesso");
        return listaTransacao
                .stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();
    }
}
