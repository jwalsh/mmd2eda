(ns mmd2eda.async
  (:require [clojure.core.async :as async :refer [<! >! go go-loop]] 
            [clojure.string :as str]))

; Channels  
(def microphone (async/chan)) ; emits raw audio bytes

(def audio-buffer (async/chan)) ; emits audio buffers

(def audio-segmenter (async/chan)) ; emits segmented audio

(def audio-queue (async/chan)) ; emits audio segments

(def transcribe-whisper (async/chan)) ; emits whisper transcripts

(def transcribe-assemblyai (async/chan)) ; emits assemblyai transcripts

(def transcribe-queue (async/chan)) ; emits transcripts to be handled

(def transcript-queue (async/chan)) ; emits final transcripts

(def activation-check (async/chan)) ; emits transcripts to check activation

(def notify-user (async/chan)) ; emits activated transcripts 

(def read-aloud (async/chan)) ; emits signal to read transcript aloud

(def clarification-prompt (async/chan)) ; emits signal to ask for clarification

(def summarize-transcript (async/chan)) ; emits signal to summarize transcript

(def topics (async/chan)) ; emits transcripts for topic extraction

(def questions (async/chan)) ; emits questions from transcripts

(def task (async/chan)) ; emits tasks from transcripts

(def translate-transcript (async/chan)) ; emits transcripts to translate

(def sentiment-analysis (async/chan)) ; emits transcripts for sentiment analysis

(def speaker-diarization (async/chan)) ; emits transcripts for speaker diarization

(def extract-concepts (async/chan)) ; emits transcripts for concept extraction

(def screenplay (async/chan)) ; emits transcripts to generate screenplay

(def clean-dialogue (async/chan)) ; emits screenplays to clean dialogue

(def dialogue-queue (async/chan)) ; emits cleaned dialogue

(def extract-characters (async/chan)) ; emits dialogue for character extraction

(def screenplay-styler (async/chan)) ; emits screenplays and characters to style

(def image-generation (async/chan)) ; emits styled screenplays for image generation

(def screenplay-queue (async/chan)) ; emits final screenplays

(def concept-graph (async/chan)) ; emits extracted concepts

(def related-concepts (async/chan)) ; emits concepts for related concept extraction

(def historical-concepts (async/chan)) ; emits concepts for historical lookup

(def concept-queue (async/chan)) ; emits final filtered concepts

(def generate-quiz (async/chan)) ; emits concepts to generate quiz  

(def recommend-content (async/chan)) ; emits concepts for content recommendation

(def add-to-knowledge-graph (async/chan)) ; emits concepts to add to knowledge graph

(def org-roam (async/chan)) ; emits concepts to add to org-roam

(def json (async/chan)) ; emits concepts to add to JSON knowledge graph

(def neo4j (async/chan)) ; emits concepts to add to Neo4j graph database

(def update-transcript-freqs (async/chan)) ; emits transcripts to update word freqs

(def summarize-tasks (async/chan)) ; emits transcripts to summarize tasks

(def add-to-gtd-inbox (async/chan)) ; emits summarized tasks to add to GTD inbox

; Process audio bytes into buffers
(defn process-audio []
  (go-loop [] 
    (let [audio (<! microphone)]
      (>! audio-buffer audio))
    (recur)))
    
; Segment audio buffers into segments
(defn segment-audio []
  (go-loop []
    (let [buffer (<! audio-buffer)]
      (doseq [segment (segment-audio buffer)]
        (>! audio-queue segment)))
    (recur)))

; Transcribe audio segments 
(defn transcribe []
  (alt!
    transcribe-whisper ([segment] (whisper-transcribe segment))
    transcribe-assemblyai ([segment] (assemblyai-transcribe segment))
    (recur)))
    
; Handle transcripts
(defn handle-transcript [transcript]
  ; emit on output channels
  (recur))
  
;; Define other processing functions

(defn -main []

  ; set up async pipelines
  (async/pipeline-async 10 
    microphone 
    process-audio)
                        
  (async/pipeline-async 10
   audio-buffer
   segment-audio)
   
  ; start pipelines
  (async/pipeline-async 10
    audio-queue 
    transcribe
    handle-transcript)
    
  ;; set up other pipelines
  
  ; run indefinitely
  (async/close! microphone))
