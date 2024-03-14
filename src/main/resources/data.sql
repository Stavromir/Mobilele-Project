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


INSERT INTO offers (`id`, `description`, `engine`, `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`, `seller_id`)
VALUES
    ('1', 'Top car!', 'PETROL', 'https://redriven.com/wp-content/uploads/2023/09/Mitsubishi-Lancer-Ralliart-1.jpg', '182000', '211111.00', 'AUTOMATIC', 'da0c23b0-0a4e-442f-a5f8-d769f96ed91e', '2010', '3', '1'),
    ('2', 'Car for family!', 'DIESEL', 'https://automedia.investor.bg/media/files/resized/uploadedfiles/640x0/98d/eae238d1fed70c52dba9b6442ffef98d-dacia-duster.jpg', '182000', '15111.00', 'AUTOMATIC', 'da0c23b0-0a4e-442f-a5f8-d769f96ed31e', '2007', '1', '1'),
    ('3', 'Top car!', 'PETROL', 'https://redriven.com/wp-content/uploads/2023/09/Mitsubishi-Lancer-Ralliart-1.jpg', '182000', '211111.00', 'AUTOMATIC', 'da0c23b0-0a4e-442f-a5f8-d769f96ed11e', '2010', '3', '1'),
    ('4', 'Top car!', 'PETROL', 'https://redriven.com/wp-content/uploads/2023/09/Mitsubishi-Lancer-Ralliart-1.jpg', '182000', '211111.00', 'AUTOMATIC', 'da0c23b0-0a4e-442f-a5f8-d769f96ed21e', '2010', '3', '1'),
    ('5', 'Top car!', 'PETROL', 'https://redriven.com/wp-content/uploads/2023/09/Mitsubishi-Lancer-Ralliart-1.jpg', '182000', '211111.00', 'AUTOMATIC', 'da0c23b0-0a4e-442f-a5f8-d769f96ed51e', '2010', '3', '2');


