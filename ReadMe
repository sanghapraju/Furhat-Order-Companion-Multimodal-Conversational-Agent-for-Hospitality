# Furhat Order Companion: Multimodal Conversational Agent for Hospitality

![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple)
![Platform](https://img.shields.io/badge/Platform-Furhat%20Robotics%20SDK-blue)
![Domain](https://img.shields.io/badge/Domain-Human--Robot%20Interaction%20(HRI)-green)

An interactive conversational agent and social robotic system designed to function as an upscale restaurant waiter. Built using the **Furhat Robotics SDK**, the system explores multimodal dialogue management, incorporating **speech synthesis, strategic gaze cues, and turn-taking dynamics** to guide diner selections and improve upsell engagement.

---

## 📌 Project Overview
* **Objective:** Design and test an autonomous virtual/social robot waiter capable of taking food and beverage orders while subtly steering customer choices via non-verbal cues (gaze, head gestures).
* **Target Environment:** Fine dining, upscale-casual restaurants, and diners seeking personalized service.
* **Technology:** Furhat SDK Desktop Launcher, Open Web Interface, and Kotlin State Engine (adapted from the fruit seller dialogue template).

---

## 🧠 Multimodal Architecture & Dialogue Design

| Modality | Type | Implementation & Role |
| :--- | :--- | :--- |
| **Voice Recognition** | Inbound | NLU for intent classification and entity extraction (e.g., wine vintage, Indian cuisine dishes, dessert options). |
| **Gaze Behavior** | Outbound | Directs user visual attention toward recommended specials and dessert categories to increase engagement. |
| **Turn-Taking Signals** | Bi-directional | Recognizes pauses, nods, and eye contact to manage conversational flow and yield speaking turns naturally. |
| **Speech Synthesis** | Outbound | Delivers contextual verbal recommendations, storytelling on cooking processes, and confirmation feedback. |

### Conversational Flow
```mermaid
graph TD
    A[Greeting & Welcome] --> B[Wine Selection: Red / White & Vintage]
    B --> C[Main Course Selection: Indian Cuisine]
    C --> D[Cooking Process Briefing & Storytelling]
    D --> E[Dessert & Complementary Suggestions]
    E --> F[Order Confirmation & Summary]
