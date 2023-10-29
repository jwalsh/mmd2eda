(ns mmd2eda.flow
  (:require [clojure.core.async :as async :refer [<! >! go go-loop]] 
            [clojure.string :as str]))

(defn log-call-to-console [message]
  (print message))

(defn log-call [message]
  (-> message
      (log-call-to-console)))

(defn notify-user [message]
  (log-call message))

(defn read-aloud [message]
  (log-call message))

(defn listen-to-microphone [file-name]
  (-> file-name
      (fill-audio-buffer)
      (create-audio-segment)
      (add-to-audio-queue)))

(defn transcribe-whisper [audio-file]
  (-> audio-file
      (transcribe-assembly-ai)
      (add-to-transcript-queue)))

(defn if-activated [tasks]
(if (check-activation)
    (-> (notify-user)
        (read-aloud)
        (prompt-for-clarification)
        (summarize-transcript)
        (translate-transcript)
        (analyze-sentiment)
        (diarize-speaker)
        (extract-concepts)
        (create-screenplay)
        (ad-to-gtd-inbox))
    (discard))
)

(defn concept-graph-updates [concepts]
  (-> concepts
      (update-concept-graph)
      (find-related-concepts)
      (find-historical-concepts)
      (add-to-concept-queue)))

(defn knowledge-graph-updates [knowledge-graph]
  (-> knowledge-graph
      (add-to-knowledge-graph)
      (persist-knowledge-graph)
      (update-transcript-frequency)))

(defn main-flow []
  (listen-to-microphone "file.wav")
  (transcribe-whisper "file.wav")
  (if-activated "tasks")
  (concept-graph-updates "concepts")
  (knowledge-graph-updates "knowledge-graph"))

(defn -main
  "The application entry point"
  [& args]
  (main-flow))
