PGDMP     !    !            	    |            barang    14.7    14.7     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    230361    barang    DATABASE     f   CREATE DATABASE barang WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_Indonesia.1252';
    DROP DATABASE barang;
                postgres    false            �            1259    238659    barang    TABLE     {  CREATE TABLE public.barang (
    id bigint NOT NULL,
    additional_info jsonb,
    created_at timestamp without time zone,
    created_by character varying(255) NOT NULL,
    gambar character varying(255),
    nama_barang character varying(255),
    nomor_seri uuid,
    stock_barang integer,
    updated_at timestamp without time zone,
    updated_by character varying(255)
);
    DROP TABLE public.barang;
       public         heap    postgres    false            �            1259    238658    barang_id_seq    SEQUENCE     v   CREATE SEQUENCE public.barang_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.barang_id_seq;
       public          postgres    false    210            �           0    0    barang_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.barang_id_seq OWNED BY public.barang.id;
          public          postgres    false    209            \           2604    238662 	   barang id    DEFAULT     f   ALTER TABLE ONLY public.barang ALTER COLUMN id SET DEFAULT nextval('public.barang_id_seq'::regclass);
 8   ALTER TABLE public.barang ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            �          0    238659    barang 
   TABLE DATA           �   COPY public.barang (id, additional_info, created_at, created_by, gambar, nama_barang, nomor_seri, stock_barang, updated_at, updated_by) FROM stdin;
    public          postgres    false    210   �       �           0    0    barang_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.barang_id_seq', 1, false);
          public          postgres    false    209            ^           2606    238666    barang barang_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.barang
    ADD CONSTRAINT barang_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.barang DROP CONSTRAINT barang_pkey;
       public            postgres    false    210            �      x������ � �     