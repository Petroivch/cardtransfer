PGDMP     7                     z            coursach    15.1    15.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16712    coursach    DATABASE     |   CREATE DATABASE coursach WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE coursach;
                postgres    false            �            1259    16713    card    TABLE     �   CREATE TABLE public.card (
    id integer NOT NULL,
    user_id integer NOT NULL,
    number character varying(50),
    date_exp character varying(30),
    cvv smallint,
    amount numeric
);
    DROP TABLE public.card;
       public         heap    postgres    false            �            1259    16718    Card_Id_seq    SEQUENCE     �   CREATE SEQUENCE public."Card_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."Card_Id_seq";
       public          postgres    false    214                       0    0    Card_Id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public."Card_Id_seq" OWNED BY public.card.id;
          public          postgres    false    215            �            1259    16719    Card_UserId_seq    SEQUENCE     �   CREATE SEQUENCE public."Card_UserId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Card_UserId_seq";
       public          postgres    false    214                       0    0    Card_UserId_seq    SEQUENCE OWNED BY     F   ALTER SEQUENCE public."Card_UserId_seq" OWNED BY public.card.user_id;
          public          postgres    false    216            �            1259    16720    transfer    TABLE     �   CREATE TABLE public.transfer (
    id integer NOT NULL,
    transaction_date timestamp with time zone,
    card_source_id integer,
    card_destination_id integer,
    amount numeric,
    result boolean
);
    DROP TABLE public.transfer;
       public         heap    postgres    false            �            1259    16725    Transfer_TransferId_seq    SEQUENCE     �   CREATE SEQUENCE public."Transfer_TransferId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public."Transfer_TransferId_seq";
       public          postgres    false    217                       0    0    Transfer_TransferId_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public."Transfer_TransferId_seq" OWNED BY public.transfer.id;
          public          postgres    false    218            �            1259    16726 	   card_user    TABLE     �   CREATE TABLE public.card_user (
    id integer NOT NULL,
    first_name character varying(30),
    last_name character varying(30),
    birthday date,
    address character varying(50),
    phone bigint
);
    DROP TABLE public.card_user;
       public         heap    postgres    false            �            1259    16729    User_id_seq    SEQUENCE     �   CREATE SEQUENCE public."User_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."User_id_seq";
       public          postgres    false    219                       0    0    User_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public."User_id_seq" OWNED BY public.card_user.id;
          public          postgres    false    220            p           2604    16730    card id    DEFAULT     d   ALTER TABLE ONLY public.card ALTER COLUMN id SET DEFAULT nextval('public."Card_Id_seq"'::regclass);
 6   ALTER TABLE public.card ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214            r           2604    16731    card_user id    DEFAULT     i   ALTER TABLE ONLY public.card_user ALTER COLUMN id SET DEFAULT nextval('public."User_id_seq"'::regclass);
 ;   ALTER TABLE public.card_user ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            q           2604    16732    transfer id    DEFAULT     t   ALTER TABLE ONLY public.transfer ALTER COLUMN id SET DEFAULT nextval('public."Transfer_TransferId_seq"'::regclass);
 :   ALTER TABLE public.transfer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            
          0    16713    card 
   TABLE DATA           J   COPY public.card (id, user_id, number, date_exp, cvv, amount) FROM stdin;
    public          postgres    false    214   c!                 0    16726 	   card_user 
   TABLE DATA           X   COPY public.card_user (id, first_name, last_name, birthday, address, phone) FROM stdin;
    public          postgres    false    219   �!                 0    16720    transfer 
   TABLE DATA           m   COPY public.transfer (id, transaction_date, card_source_id, card_destination_id, amount, result) FROM stdin;
    public          postgres    false    217   "                  0    0    Card_Id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."Card_Id_seq"', 2, true);
          public          postgres    false    215                       0    0    Card_UserId_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Card_UserId_seq"', 1, false);
          public          postgres    false    216                       0    0    Transfer_TransferId_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public."Transfer_TransferId_seq"', 60, true);
          public          postgres    false    218                       0    0    User_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."User_id_seq"', 2, true);
          public          postgres    false    220            t           2606    16734    card Card_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.card
    ADD CONSTRAINT "Card_pkey" PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.card DROP CONSTRAINT "Card_pkey";
       public            postgres    false    214            v           2606    16736    transfer Transfer_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.transfer
    ADD CONSTRAINT "Transfer_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.transfer DROP CONSTRAINT "Transfer_pkey";
       public            postgres    false    217            x           2606    16738    card_user User_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.card_user
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.card_user DROP CONSTRAINT "User_pkey";
       public            postgres    false    219            y           2606    16739    card UserId    FK CONSTRAINT     z   ALTER TABLE ONLY public.card
    ADD CONSTRAINT "UserId" FOREIGN KEY (user_id) REFERENCES public.card_user(id) NOT VALID;
 7   ALTER TABLE ONLY public.card DROP CONSTRAINT "UserId";
       public          postgres    false    219    3192    214            z           2606    16744    transfer card_destination    FK CONSTRAINT     �   ALTER TABLE ONLY public.transfer
    ADD CONSTRAINT card_destination FOREIGN KEY (card_destination_id) REFERENCES public.card(id) NOT VALID;
 C   ALTER TABLE ONLY public.transfer DROP CONSTRAINT card_destination;
       public          postgres    false    217    214    3188            {           2606    16749    transfer card_source    FK CONSTRAINT     �   ALTER TABLE ONLY public.transfer
    ADD CONSTRAINT card_source FOREIGN KEY (card_source_id) REFERENCES public.card(id) NOT VALID;
 >   ALTER TABLE ONLY public.transfer DROP CONSTRAINT card_source;
       public          postgres    false    214    217    3188            
   :   x�3�4�4D@}#�0���9�Y�prq�N3�B�����T�Ą+F��� ���         V   x�3��,K��e�Fƺ�F@�阒R�Z\l�ihdlbjfnai�e��ZR&��---u���؈���������Ѐ+F��� �~         `   x��л�0��ڙ"=
��y�,���"�)�I��W��H���SulS�I�;��XD�(F�p*2��JE�bjZᯘ���ݓ0�a�=�� P+Gf     