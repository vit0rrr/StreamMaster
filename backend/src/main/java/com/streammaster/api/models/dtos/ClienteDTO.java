import java.util.List;

public class Exercicio1 {
    public int somaQuadradosImpares(List<Integer> numeros) {
        return numeros.stream()
            .filter(n -> n % 2 != 0)
            .mapToInt(n -> n * n)
            .sum(); 
    }
}


public class Exercicio2 {
    public String formatarNomes(List<String> nomes) {
        return nomes.stream()
            .map(String::toUpperCase)
            .collect(Collectors.joining(", "));
    }
}


public class Exercicio3 {
    public int encontrarMaior(List<Integer> numeros) {
        return numeros.stream()
            .max(Integer::compare)
            .orElseThrow();
    }
}


public class Exercicio4 {
    public List<Integer> limparDados(List<Integer> numeros) {
        return numeros.stream()
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }
}



import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exercicio5 {
    public Map<String, Long> contarPalavras(List<String> palavras) {
        return palavras.stream()
            .collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()
            ));
    }
}


import java.util.stream.IntStream;

public class Desafio1 {
    public boolean isPrimo(int n) {
       if(n<=1) return false;
       return IntStream.range(2, n).noneMatch(i -> n % i == 0);
    }
}


import java.util.stream.IntStream;

public class Desafio2 {
    public boolean isPalindromo(String s) {
        String limpa = s.toLowerCase();
        int len = limpa.length();
        return IntStream.range(0, len / 2).allMatch(i -> limpa.charAt(i) == limpa.charAt(len - i - 1));
    }
}

public class Desafio3 {
    public long contarVogais(String s) {
        return s.toLowerCase().chars()
            .filter(c -> "aeiou".indexOf(c) != -1)
            .count();
    }
}


import java.util.List;
import java.util.stream.Collectors;

public class Desafio4 {
    public List<String> combinarListas(List<Integer> lista1, List<Integer> lista2) {
        return lista1.stream()
            .flatMap(i -> lista2.stream().map(j -> i + "-" + j))
            .collect(Collectors.toList());
    }
}

import java.util.List;

public class Desafio5 {
    public String maiorPalavra(List<String> palavras) {
        return palavras.stream()
            .reduce((p1, p2) -> p1.length() >= p2.length() ? p1 : p2)
            .orElse("");
    }
}