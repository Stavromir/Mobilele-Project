INSERT INTO users (id, email, first_name, last_name, active, password)
VALUES
    (1, 'admin@abv.bg', 'Admin', 'Adminov', 1, '1ae1fe49cc496523b621817d613d4bf17423ea8f7bf4ae903a4f336f8ee8465a9289932cfdde59c26559880382849453'),
    (2, 'user@abv.bg', 'User', 'Userov', 1, '1ae1fe49cc496523b621817d613d4bf17423ea8f7bf4ae903a4f336f8ee8465a9289932cfdde59c26559880382849453');

INSERT INTO roles (id, role)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);

INSERT INTO brands (id, name)
VALUES
    (1, 'Toyota'),
    (2, 'Nisan'),
    (3, 'Mitsubishi');

INSERT INTO models (id, name, category, brand_id)
VALUES
    (1, 'Supra', 'CAR', 1),
    (2, 'Note', 'CAR', 2),
    (3, 'Lancer', 'CAR', 3),
    (4, 'Colt', 'CAR', 3);


