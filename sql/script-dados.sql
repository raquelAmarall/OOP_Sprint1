-- ============================================
-- SCRIPT DE DADOS - SISTEMA MOTIVA
-- ============================================

-- 1. EQUIPES DE MANUTENÇÃO
INSERT INTO equipe_manutencao (nome)
VALUES ('Equipe de Manutenção Norte');

INSERT INTO equipe_manutencao (nome)
VALUES ('Equipe de Manutenção Sul');


-- 2. TRECHOS DE RODOVIA
INSERT INTO trecho_rodovia (
    quilometro_inicial,
    quilometro_final,
    nivel_vegetacao,
    nome,
    umido,
    monitorado
) VALUES (
    110,
    330,
    5,
    'BR-116',
    1,
    1
);

INSERT INTO trecho_rodovia (
    quilometro_inicial,
    quilometro_final,
    nivel_vegetacao,
    nome,
    umido,
    monitorado
) VALUES (
    500,
    900,
    10,
    'BR-230',
    1,
    0
);

INSERT INTO trecho_rodovia (
    quilometro_inicial,
    quilometro_final,
    nivel_vegetacao,
    nome,
    umido,
    monitorado
) VALUES (
    123,
    456,
    15,
    'BR-123',
    0,
    1
);

INSERT INTO trecho_rodovia (
    quilometro_inicial,
    quilometro_final,
    nivel_vegetacao,
    nome,
    umido,
    monitorado
) VALUES (
    700,
    800,
    1,
    'BR-500',
    0,
    0
);


-- 3. INTERVENÇÕES OPERACIONAIS
INSERT INTO intervencao_operacional (
    tipo,
    descricao,
    id_trecho
) VALUES (
    'Roçada Mecanizada',
    'Intervenção de roçada mecanizada',
    3
);

INSERT INTO intervencao_operacional (
    tipo,
    descricao,
    id_trecho
) VALUES (
    'Pulverização',
    'Intervenção de pulverização',
    2
);


-- 4. RELATÓRIOS DE PRIORIDADE
INSERT INTO relatorio_prioridade (
    qt_urgente,
    qt_critico,
    qt_atencao,
    qt_normal,
    resumo
) VALUES (
    1,
    1,
    1,
    1,
    'Relatório inicial de teste do sistema MOTIVA'
);

COMMIT;