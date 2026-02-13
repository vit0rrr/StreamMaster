// Simula API de Usuário (Rápida: 1s)
const getUsuario = () => {
    return new Promise(resolve => setTimeout(() => resolve("Ana Silva"), 1000));
};

// Simula API de Pedidos (Lenta: 3s)
const getPedidos = () => {
    return new Promise(resolve => setTimeout(() => resolve(["Pedido #1", "Pedido #2"]), 3000));
};

// Simula API de Notificações (Média: 2s)
const getNotificacoes = () => {
    return new Promise(resolve => setTimeout(() => resolve(5), 2000));
};


const carregarDashboard = async () => {

    console.time("Dashboard carregado");
    try {
        const [usuario, pedidos, notificacoes] = await Promise.all([
            getUsuario(),
            getPedidos(),
            getNotificacoes()
        ]);

        console.log("Dashboard carregado:");
        console.log("Usuário:", usuario);
        console.log("Pedidos:", pedidos);
        console.log("Notificações:", notificacoes);

    } catch (error) {
        console.error("Erro ao carregar dashboard:", error);
    }finally {
        console.timeEnd("Dashboard carregado");
    }
}

carregarDashboard();
