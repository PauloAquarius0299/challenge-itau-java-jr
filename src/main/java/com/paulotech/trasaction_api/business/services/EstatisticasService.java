package com.paulotech.trasaction_api.business.services;

import com.paulotech.trasaction_api.controllers.dtos.EstatisticasResponseDTO;
import com.paulotech.trasaction_api.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransationService transationService;

    public EstatisticasResponseDTO calcularEstatisticasTrans(Integer intervalorBusca){
        log.info("Iniciando buscas de estatisticas e transações pelo periodo de tempo" + intervalorBusca);
        List<TransacaoRequestDTO> transacoes = transationService.buscarTransacoes(intervalorBusca);

        if(transacoes.isEmpty()){
            return new EstatisticasResponseDTO(0l, 0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::value).summaryStatistics();

        log.info("Estatisticas retornandas com sucesso");

        return new EstatisticasResponseDTO(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax()
        );
    }
}
