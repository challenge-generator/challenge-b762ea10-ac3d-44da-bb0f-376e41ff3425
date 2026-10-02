# Manejo de errores y validaciones en un sistema de comentarios E2E

El sistema de comentarios en producción está experimentando problemas con el manejo de errores y validaciones. Los usuarios reportan que sus comentarios no se procesan correctamente cuando se ingresan datos inválidos o cuando ocurren fallos en el sistema. El objetivo es mejorar el manejo de errores y validaciones en el sistema para asegurar que los comentarios se procesen correctamente y que los usuarios reciban feedback apropiado en caso de errores.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E prod comments 1790905766 |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Identificación de errores y validaciones actuales

**Objetivo:** Identificar los errores y validaciones actuales en el sistema de comentarios

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza el sistema de comentarios existente para identificar los puntos de fallo y las validaciones actuales.
- Documenta los tipos de errores y validaciones que se están manejando actualmente y aquellos que no se están manejando.

**Entregable:** Documento que describe los errores y validaciones actuales en el sistema de comentarios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes tipos de datos que se ingresan en el sistema y las posibles formas en que pueden ser inválidos.
- Piensa en los diferentes escenarios en los que el sistema puede fallar y cómo se están manejando actualmente.

</details>

### Fase 2: Diseño de mejoras en el manejo de errores y validaciones

**Objetivo:** Diseñar mejoras en el manejo de errores y validaciones en el sistema de comentarios

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Propón mejoras en el manejo de errores y validaciones basándote en el análisis realizado en la fase anterior.
- Considera diferentes estrategias para manejar errores y validaciones, como validaciones en el front-end, validaciones en el back-end, y manejo de errores en diferentes capas del sistema.

**Entregable:** Documento que describe las mejoras propuestas en el manejo de errores y validaciones.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga diferentes estrategias para manejar errores y validaciones en sistemas similares.
- Considera los pros y contras de cada estrategia y cómo se alinean con las necesidades del sistema de comentarios.

</details>

### Fase 3: Implementación y prueba de las mejoras propuestas

**Objetivo:** Implementar y probar las mejoras propuestas en el manejo de errores y validaciones

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Implementa las mejoras propuestas en el sistema de comentarios.
- Realiza pruebas para asegurar que las mejoras están funcionando correctamente y que los usuarios reciben feedback apropiado en caso de errores.

**Entregable:** Sistema de comentarios mejorado con el manejo de errores y validaciones implementado y probado.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza pruebas unitarias y de integración para asegurar que las mejoras están funcionando correctamente.
- Considera diferentes escenarios de prueba para asegurar que las mejoras están cubriendo todos los casos de uso.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los errores y validaciones en el contexto del sistema de comentarios?
- **paraQueSirve**: ¿Para qué sirven las mejoras en el manejo de errores y validaciones en el sistema de comentarios?
- **comoSeUsa**: ¿Cómo se pueden implementar y probar las mejoras en el manejo de errores y validaciones?
- **erroresComunes**: ¿Cuáles son los errores comunes que se pueden encontrar en el sistema de comentarios y cómo se pueden manejar?
- **queDecisionesImplica**: ¿Qué decisiones implica el diseño y la implementación de mejoras en el manejo de errores y validaciones?

## Criterios de Evaluacion

- Identificación correcta de los errores y validaciones actuales en el sistema de comentarios.
- Propuesta de mejoras efectivas en el manejo de errores y validaciones.
- Implementación y prueba exitosa de las mejoras propuestas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
