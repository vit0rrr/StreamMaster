// <T> define que vamos receber um TIpo (ex: HTMLButtonElement)
// O padrão (= HTMLElement) é usado se você não especificar nada
export function selecionar<T = HTMLElement>(seletor: string): T | null {
    const elemento = document.querySelector(seletor);
    return elemento as T | null;
}

