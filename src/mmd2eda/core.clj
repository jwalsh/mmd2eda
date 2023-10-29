(ns mmd2eda.core
  (:gen-class))

(defn listen-to-microphone []
  (println "Listening to microphone..."))

(defn fill-audio-buffer [buffer]
  (println "Filling audio buffer..."))

(defn create-audio-segment [file-name]
  (println "Creating audio segment..."))

(defn add-to-audio-queue [audio-segment]
  (println "Adding to audio queue..."))

(defn transcribe-assembly-ai [file-name]
  (println "Transcribing with AssemblyAI..."))

(defn add-to-transcript-queue [transcript]
  (println "Adding to transcript queue..."))

(defn summarize-transcript [transcript]
  (println "Summarizing transcript..."))

(defn save-summary [summary]
  (println "Saving summary..."))

(defn -main
  "Minimal flow for audio processing."
  [& args]
  (-> (listen-to-microphone)
      (fill-audio-buffer)
      (create-audio-segment)
      (add-to-audio-queue)
      (transcribe-assembly-ai)
      (add-to-transcript-queue)
      (summarize-transcript)
      (save-summary)))


