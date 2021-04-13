INSERT INTO chapters (id, name) VALUES (1, 'chapter 1');
INSERT INTO chapters (id, name) VALUES (2, 'chapter 2');
INSERT INTO chapters (id, name) VALUES (3, 'chapter 3');

INSERT INTO paragraphs (id, name, chapter_id) VALUES (1, 'par 1', 1);
INSERT INTO paragraphs (id, name, chapter_id) VALUES (2, 'par 2', 1);
INSERT INTO paragraphs (id, name, chapter_id) VALUES (3, 'par 1', 2);

INSERT INTO steps (id, paragraph_id, theory, question, correct_answers, answers_explanations, has_answer) VALUES (1, 1, 'Some text theory', '{"type": "checkbox", "answers": [{"id": 1, "answer": "ans 1"}, {"id": 2, "answer": "ans 2"}, {"id": 3, "answer": "ans 3"}]}', '[1,2]', '[{"id": 1, "explanation": "explanation 1"},{"id": 2, "explanation": "explanation 2"}]', TRUE);
INSERT INTO steps (id, paragraph_id, theory, question, correct_answers, answers_explanations, has_answer) VALUES (2, 1, 'Some text theory 2', '{"type": "checkbox", "answers": [{"id": 1, "answer": "ans 1"}, {"id": 2, "answer": "ans 2"}, {"id": 3, "answer": "ans 3"}]}', '[1,2]', '[{"id": 1, "explanation": "explanation 1"},{"id": 2, "explanation": "explanation 2"}]', TRUE);
INSERT INTO steps (id, paragraph_id, theory, question, correct_answers, has_answer) VALUES (3, 1, 'Some text theory 3', '{"type": "free"}', '[]', FALSE);
INSERT INTO steps (id, paragraph_id, theory, question, correct_answers, has_answer) VALUES (4, 1, 'Some text theory 4', '{"type": "free"}', '[]', FALSE);
INSERT INTO steps (id, paragraph_id, theory, question, correct_answers, has_answer) VALUES (5, 2, 'Some text theory 5', '{"type": "free"}', '[]', FALSE);
