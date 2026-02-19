"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const btnInscrever = document.querySelector(".btn--primary");
const listaModulos = document.getElementById('lista-modulos');
const btnCarregar = document.getElementById('btn-carregar-modulos');
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));
const enviarInscricaoParaServidor = (dados) => __awaiter(void 0, void 0, void 0, function* () {
    console.log(`[API] Recebendo inscrição para: ${dados.email}`);
    yield delay(2000);
    return {
        mensagem: "Inscrição realizada com sucesso!",
        matriculaId: Math.floor(Math.random() * 10000),
        status: 'sucesso'
    };
});
const carregarModulosAPI = () => __awaiter(void 0, void 0, void 0, function* () {
    yield delay(1000);
    const dadosFakes = Array.from({ length: 50 }, (_, i) => ({
        id: i + 1,
        nome: `Módulo ${i + 1}: Java Streams Avançado`,
        duracao: "45m"
    }));
    return {
        sucesso: true,
        total: dadosFakes.length,
        dados: dadosFakes
    };
});
function tratarInscricao() {
    return __awaiter(this, void 0, void 0, function* () {
        if (!btnInscrever)
            return;
        const textoOriginal = btnInscrever.innerText;
        btnInscrever.innerText = "Processando...";
        btnInscrever.disabled = true;
        btnInscrever.style.cursor = "wait";
        try {
            const pedido = { email: "aluno@streammaster.com" };
            const resposta = yield enviarInscricaoParaServidor(pedido);
            if (resposta.status === 'sucesso') {
                btnInscrever.innerText = `Matrícula #${resposta.matriculaId} Confirmada! 🎉`;
                btnInscrever.classList.remove("btn--primary");
                btnInscrever.classList.add("btn--success");
                btnInscrever.style.cursor = "default";
            }
        }
        catch (error) {
            alert("Ops! Falha na conexão.");
            btnInscrever.innerText = textoOriginal;
            btnInscrever.disabled = false;
            btnInscrever.style.cursor = "pointer";
        }
    });
}
function carregarGrade() {
    return __awaiter(this, void 0, void 0, function* () {
        if (!btnCarregar || !listaModulos)
            return;
        console.time("Performance de Renderização");
        btnCarregar.innerText = "Carregando...";
        const resposta = yield carregarModulosAPI();
        const fragmento = document.createDocumentFragment();
        resposta.dados.forEach(({ nome, duracao }) => {
            const li = document.createElement('li');
            li.className = 'module-item';
            li.innerHTML = `
            <strong>${nome}</strong>
            <span style="color: #666;">${duracao}</span>
        `;
            fragmento.appendChild(li);
        });
        listaModulos.appendChild(fragmento);
        btnCarregar.remove();
        console.timeEnd("Performance de Renderização");
    });
}
if (btnInscrever) {
    btnInscrever.addEventListener("click", tratarInscricao);
}
if (btnCarregar) {
    btnCarregar.addEventListener("click", carregarGrade);
}
//# sourceMappingURL=index.js.map