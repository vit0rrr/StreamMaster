/* ==========================================================================
   1. UTILITÁRIOS GENÉRICOS (A "Caixa de Ferramentas")
   ========================================================================== */

// <T>: O tipo do elemento HTML que esperamos
function selecionar<T>(seletor: string): T | null {
    return document.querySelector(seletor) as T | null;
}

// <T>: O tipo de dado que a API vai retornar
function requestFake<T>(dados: T, ms: number = 1000): Promise<T> {
    return new Promise(resolve => setTimeout(() => resolve(dados), ms));
}

/* ==========================================================================
   2. INTERFACES (Tipos)
   ========================================================================== */
interface Modulo { id: number; nome: string; duracao: string; }
interface GetModulosResponse { sucesso: boolean; total: number; dados: Modulo[]; }
interface InscricaoRequest { email: string; }
interface InscricaoResponse { mensagem: string; matriculaId: number; status: 'sucesso' | 'erro'; }

/* ==========================================================================
   3. SELEÇÃO DO DOM (Usando o Genérico)
   ========================================================================== */
// Note o uso de <HTML...> entre os sinais de maior/menor
const btnInscrever = selecionar<HTMLButtonElement>(".btn--primary");
const listaModulos = selecionar<HTMLUListElement>("#lista-modulos"); 
const btnCarregar = selecionar<HTMLButtonElement>("#btn-carregar-modulos");

/* ==========================================================================
   4. CHAMADAS DE API (Usando o Genérico)
   ========================================================================== */

const enviarInscricao = async (req: InscricaoRequest) => {
    console.log(`Enviando: ${req.email}`);
    return requestFake<InscricaoResponse>({
        mensagem: "Cadastrado!",
        matriculaId: Date.now(),
        status: 'sucesso'
    }, 2000);
};

const buscarModulos = async () => {
    const dadosFakes: Modulo[] = Array.from({ length: 50 }, (_, i) => ({
        id: i + 1,
        nome: `Módulo ${i + 1}: Generics com TypeScript`,
        duracao: "45m"
    }));

    
    return requestFake<GetModulosResponse>({
        sucesso: true,
        total: dadosFakes.length,
        dados: dadosFakes
    }, 1000);
};



async function tratarInscricao(): Promise<void> {
    if (!btnInscrever) return;
    const textoOriginal = btnInscrever.innerText;
    
    btnInscrever.innerText = "Processando...";
    btnInscrever.disabled = true;

    try {
        const resultado = await enviarInscricao({ email: "teste@email.com" });
        
        if (resultado.status === 'sucesso') {
            btnInscrever.innerText = `ID: ${resultado.matriculaId} - Sucesso!`;
            btnInscrever.classList.add("btn--success");
        }
    } catch (e) {
        btnInscrever.innerText = textoOriginal;
        btnInscrever.disabled = false;
    }
}

async function carregarGrade(): Promise<void> {
    if (!btnCarregar || !listaModulos) return;

    btnCarregar.innerText = "Carregando...";
    
    // Chamada da API fake genérica
    const resposta = await buscarModulos();
    
    const fragmento = document.createDocumentFragment();

    resposta.dados.forEach(({ nome, duracao }) => {
        const li = document.createElement('li');
        li.className = 'module-item';
        li.innerHTML = `<strong>${nome}</strong> <span style="color:#666">${duracao}</span>`;
        fragmento.appendChild(li);
    });

    listaModulos.appendChild(fragmento);
    btnCarregar.remove();
}

// Listeners
if (btnInscrever) btnInscrever.addEventListener("click", tratarInscricao);
if (btnCarregar) btnCarregar.addEventListener("click", carregarGrade);