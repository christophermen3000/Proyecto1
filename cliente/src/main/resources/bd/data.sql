INSERT INTO pais (pais_id, pais_nombre) VALUES (1,'Peru') ON CONFLICT (pais_id) DO NOTHING;
INSERT INTO pais (pais_id, pais_nombre) VALUES (2,'Argentina') ON CONFLICT (pais_id) DO NOTHING;
INSERT INTO pais (pais_id, pais_nombre) VALUES (3,'Brasil') ON CONFLICT (pais_id) DO NOTHING;