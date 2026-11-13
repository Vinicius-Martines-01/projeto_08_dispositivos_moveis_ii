# FATEC Diadema - App Curso DSM

Este é um aplicativo Android desenvolvido para promover o curso de **Desenvolvimento de Software Multiplataforma (DSM)** da FATEC Diadema. O app apresenta informações sobre o curso, o processo seletivo (Vestibular) e um vídeo institucional com controles customizados.

## 📱 Funcionalidades

- **Tela Inicial (Sobre o Curso):** Apresentação detalhada do curso, perfil do profissional e mercado de trabalho.
- **Informações do Vestibular:** Dicas, links úteis e explicação sobre como ingressar na FATEC.
- **Vídeo Institucional:** Player de vídeo integrado (`VideoView`) com:
    - Botão Play/Pause.
    - Barra de progresso (`SeekBar`) interativa.
    - Atualização de tempo em tempo real.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Plataforma:** Android SDK
- **Componentes:** `VideoView`, `SeekBar`, `Intents`, `ScrollView`, `ConstraintLayout`/`LinearLayout`.
- **Estilização:** Identidade visual baseada nas cores da FATEC (Vermelho institucional).

## 🚀 Como Executar

1. Clone o repositório.
2. Abra o projeto no **Android Studio**.
3. **Importante:** Certifique-se de adicionar um arquivo de vídeo (ex: `.mp4`) na pasta `app/src/main/res/raw/` com o nome `video_curso.mp4` para que o player funcione corretamente.
4. Sincronize o Gradle e execute o app em um emulador ou dispositivo físico.

---
Desenvolvido como projeto prático para a disciplina de Dispositivos Móveis.