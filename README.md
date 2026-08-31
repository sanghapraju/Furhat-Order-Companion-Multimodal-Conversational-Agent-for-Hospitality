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
🧪 Iterative User Testing

The system underwent three distinct evaluation iterations, progressing from a virtual agent to the physical Furhat head:

Iteration 1 (Virtual Furhat): Users reported speech overlap when the agent cut in prematurely.

Adjustment: Refined response timing delays and endpointing to eliminate speech interruption.

Iteration 2 (Virtual Furhat): Interaction was clear, but the conversational cadence felt rigid.

Adjustment: Integrated pauses, smiles, and affirmative head nods to simulate human-like dialogue flow.

Iteration 3 (Physical Furhat): Tested in a closed-room dining setup at eye level. Identified challenges with strict pronunciation expectations and rigid dialogue pathways, highlighting the need for greater conversational spontaneity.

🔍 Edge Case & Dialogue Analysis

Intent & NLU Edge Cases (Worst-Case Scenarios)

Out-of-Scope Fallbacks: When users inquired about off-menu items (e.g., lasagna), Furhat repeatedly defaulted to fallback prompts ("Sorry, I didn't understand that") instead of dynamically redirecting to available specials.

Constrained Entity Bottlenecks: When offered a choice between red or white wine, a user response of "both" caused the agent to remain idle due to strict slot-filling criteria[cite: 1].

Indirect Affirmations: Pragmatic affirmations such as "that would be nice" failed intent mapping, triggering repeated clarification prompts[cite: 1].

Turn-Taking Latency: Furhat issued premature timeout prompts ("Sorry, I didn't hear you") before the user could finish speaking[cite: 1].

Emergent Successes (Best-Case Scenarios)

Zero-Shot Slot Filling: When asked an open dessert question, users spontaneously answered "Gulab Jamun"[cite: 1]. Furhat extracted the entity, bypassed listing the dessert options, and directly confirmed the order[cite: 1].

Compound Entity Extraction: Handled multi-item compound utterances in a single conversational turn (e.g., "2 Biryani and 1 Palak Paneer"), accurately mapping dish names and quantities simultaneously[cite: 1].

🚀 Future Work & Recommendations

Enhanced NLU Flexibility: Improve intent recognition to accommodate diverse accents, conversational hedges, and indirect responses[cite: 1].

Dialogue Backtracking: Introduce conversational rollback states (e.g., "Go back", "Change selection") so users can modify choices without breaking dialogue flow[cite: 1].

Advanced Attention Cueing: Further synchronize robot gaze behaviors with visual menu interfaces or dish displays to reinforce recommendations
