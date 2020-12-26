PGDMP     1    '                x            control_inventarios    12.3    12.3 4    :           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ;           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            <           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            =           1262    16393    control_inventarios    DATABASE     �   CREATE DATABASE control_inventarios WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
 #   DROP DATABASE control_inventarios;
                postgres    false            �            1259    16441    compras    TABLE     �   CREATE TABLE public.compras (
    id integer NOT NULL,
    id_productos integer NOT NULL,
    precio bigint NOT NULL,
    kilos double precision NOT NULL,
    fecha date NOT NULL,
    id_usuario integer NOT NULL,
    total bigint
);
    DROP TABLE public.compras;
       public         heap    postgres    false            �            1259    16439    compras_id_productos_seq    SEQUENCE     �   CREATE SEQUENCE public.compras_id_productos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.compras_id_productos_seq;
       public          postgres    false    206            >           0    0    compras_id_productos_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.compras_id_productos_seq OWNED BY public.compras.id_productos;
          public          postgres    false    205            �            1259    16437    compras_id_seq    SEQUENCE     �   CREATE SEQUENCE public.compras_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.compras_id_seq;
       public          postgres    false    206            ?           0    0    compras_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.compras_id_seq OWNED BY public.compras.id;
          public          postgres    false    204            �            1259    16467    compras_id_usuario_seq    SEQUENCE     �   CREATE SEQUENCE public.compras_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.compras_id_usuario_seq;
       public          postgres    false    206            @           0    0    compras_id_usuario_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.compras_id_usuario_seq OWNED BY public.compras.id_usuario;
          public          postgres    false    212            �            1259    16431 	   productos    TABLE     f   CREATE TABLE public.productos (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL
);
    DROP TABLE public.productos;
       public         heap    postgres    false            �            1259    16429    productos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.productos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.productos_id_seq;
       public          postgres    false    203            A           0    0    productos_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.productos_id_seq OWNED BY public.productos.id;
          public          postgres    false    202            �            1259    16461    usuarios    TABLE     �   CREATE TABLE public.usuarios (
    id integer NOT NULL,
    tipo character varying(50) NOT NULL,
    nombre character varying(50) NOT NULL
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false            �            1259    16459    usuarios_id_seq    SEQUENCE     �   CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public          postgres    false    211            B           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    210            �            1259    16452    ventas    TABLE     �   CREATE TABLE public.ventas (
    id integer NOT NULL,
    id_productos integer NOT NULL,
    precio bigint NOT NULL,
    kilos double precision NOT NULL,
    fecha date NOT NULL,
    id_usuario integer NOT NULL,
    total bigint
);
    DROP TABLE public.ventas;
       public         heap    postgres    false            �            1259    16450    ventas_id_productos_seq    SEQUENCE     �   CREATE SEQUENCE public.ventas_id_productos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.ventas_id_productos_seq;
       public          postgres    false    209            C           0    0    ventas_id_productos_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.ventas_id_productos_seq OWNED BY public.ventas.id_productos;
          public          postgres    false    208            �            1259    16448    ventas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ventas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.ventas_id_seq;
       public          postgres    false    209            D           0    0    ventas_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.ventas_id_seq OWNED BY public.ventas.id;
          public          postgres    false    207            �            1259    16479    ventas_id_usuario_seq    SEQUENCE     �   CREATE SEQUENCE public.ventas_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.ventas_id_usuario_seq;
       public          postgres    false    209            E           0    0    ventas_id_usuario_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.ventas_id_usuario_seq OWNED BY public.ventas.id_usuario;
          public          postgres    false    213            �
           2604    16444 
   compras id    DEFAULT     h   ALTER TABLE ONLY public.compras ALTER COLUMN id SET DEFAULT nextval('public.compras_id_seq'::regclass);
 9   ALTER TABLE public.compras ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    204    206            �
           2604    16445    compras id_productos    DEFAULT     |   ALTER TABLE ONLY public.compras ALTER COLUMN id_productos SET DEFAULT nextval('public.compras_id_productos_seq'::regclass);
 C   ALTER TABLE public.compras ALTER COLUMN id_productos DROP DEFAULT;
       public          postgres    false    206    205    206            �
           2604    16469    compras id_usuario    DEFAULT     x   ALTER TABLE ONLY public.compras ALTER COLUMN id_usuario SET DEFAULT nextval('public.compras_id_usuario_seq'::regclass);
 A   ALTER TABLE public.compras ALTER COLUMN id_usuario DROP DEFAULT;
       public          postgres    false    212    206            �
           2604    16434    productos id    DEFAULT     l   ALTER TABLE ONLY public.productos ALTER COLUMN id SET DEFAULT nextval('public.productos_id_seq'::regclass);
 ;   ALTER TABLE public.productos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            �
           2604    16464    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210    211            �
           2604    16455 	   ventas id    DEFAULT     f   ALTER TABLE ONLY public.ventas ALTER COLUMN id SET DEFAULT nextval('public.ventas_id_seq'::regclass);
 8   ALTER TABLE public.ventas ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    207    209            �
           2604    16456    ventas id_productos    DEFAULT     z   ALTER TABLE ONLY public.ventas ALTER COLUMN id_productos SET DEFAULT nextval('public.ventas_id_productos_seq'::regclass);
 B   ALTER TABLE public.ventas ALTER COLUMN id_productos DROP DEFAULT;
       public          postgres    false    208    209    209            �
           2604    16481    ventas id_usuario    DEFAULT     v   ALTER TABLE ONLY public.ventas ALTER COLUMN id_usuario SET DEFAULT nextval('public.ventas_id_usuario_seq'::regclass);
 @   ALTER TABLE public.ventas ALTER COLUMN id_usuario DROP DEFAULT;
       public          postgres    false    213    209            0          0    16441    compras 
   TABLE DATA           \   COPY public.compras (id, id_productos, precio, kilos, fecha, id_usuario, total) FROM stdin;
    public          postgres    false    206   ?9       -          0    16431 	   productos 
   TABLE DATA           /   COPY public.productos (id, nombre) FROM stdin;
    public          postgres    false    203   	:       5          0    16461    usuarios 
   TABLE DATA           4   COPY public.usuarios (id, tipo, nombre) FROM stdin;
    public          postgres    false    211   g:       3          0    16452    ventas 
   TABLE DATA           [   COPY public.ventas (id, id_productos, precio, kilos, fecha, id_usuario, total) FROM stdin;
    public          postgres    false    209   �:       F           0    0    compras_id_productos_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.compras_id_productos_seq', 1, true);
          public          postgres    false    205            G           0    0    compras_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.compras_id_seq', 82, true);
          public          postgres    false    204            H           0    0    compras_id_usuario_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.compras_id_usuario_seq', 2, true);
          public          postgres    false    212            I           0    0    productos_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.productos_id_seq', 9, true);
          public          postgres    false    202            J           0    0    usuarios_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.usuarios_id_seq', 21, true);
          public          postgres    false    210            K           0    0    ventas_id_productos_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.ventas_id_productos_seq', 1, false);
          public          postgres    false    208            L           0    0    ventas_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.ventas_id_seq', 24, true);
          public          postgres    false    207            M           0    0    ventas_id_usuario_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.ventas_id_usuario_seq', 1, false);
          public          postgres    false    213            �
           2606    16447    compras compras_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.compras DROP CONSTRAINT compras_pkey;
       public            postgres    false    206            �
           2606    16436    productos productos_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.productos DROP CONSTRAINT productos_pkey;
       public            postgres    false    203            �
           2606    16466    usuarios usuarios_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    211            �
           2606    16458    ventas ventas_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.ventas
    ADD CONSTRAINT ventas_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.ventas DROP CONSTRAINT ventas_pkey;
       public            postgres    false    209            �
           2606    16537 !   compras compras_id_productos_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_id_productos_fkey FOREIGN KEY (id_productos) REFERENCES public.productos(id) NOT VALID;
 K   ALTER TABLE ONLY public.compras DROP CONSTRAINT compras_id_productos_fkey;
       public          postgres    false    206    2723    203            �
           2606    16532    ventas id_productos    FK CONSTRAINT     �   ALTER TABLE ONLY public.ventas
    ADD CONSTRAINT id_productos FOREIGN KEY (id_productos) REFERENCES public.productos(id) NOT VALID;
 =   ALTER TABLE ONLY public.ventas DROP CONSTRAINT id_productos;
       public          postgres    false    209    2723    203            �
           2606    16474    compras id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.compras
    ADD CONSTRAINT id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id) NOT VALID;
 <   ALTER TABLE ONLY public.compras DROP CONSTRAINT id_usuario;
       public          postgres    false    206    211    2729            �
           2606    16585    ventas id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.ventas
    ADD CONSTRAINT id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id) NOT VALID;
 ;   ALTER TABLE ONLY public.ventas DROP CONSTRAINT id_usuario;
       public          postgres    false    2729    211    209            0   �   x��QA� <�_��E����G����d����.8��T� !dSlp��	s�j�w�޾8䡃#�W(�<t�e�kҌ	G##�yB�ad�XԐ"�Gɉ�ۗ��jz-+s����i��'X��n��'��59��n��FF,	�!f�k[���Gs�u��B�5ϚaϚ=�CP��w�P~�����Z�      -   N   x��M
� ���):APA?wi3�A����s�x.�'$��A����,;U8"�zu�+\t��OmuCRjν'�\M      5   j   x�]�A@0��u�)�@L�p��FR���X8=a���/�Rk��y��&�!49� �[a%P��lA"�Svq�7V�D�I2��+>���ԺJ����]A��~WC	���B�      3   s   x�����0�Ћ#v��%������ �B 03/B�0 ҕ��O���nܞ��N[�
�_�/g�whs,�(��}?��x�'��c�=�a	:)[)캣S��3Z_H�     