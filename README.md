

## REST API

### Recipe object format

```json
{
   "name": "Warming Ginger Tea",
   "description": "Ginger tea is a warming drink for cool weather, ...",
   "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
   "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
```

| Key         | Description                                                                             |
|-------------|-----------------------------------------------------------------------------------------|
| name        | Name of the recipe in form of `String`,<br/>must contain at least one character         |
| description | Description of the recipe in form of `String`,<br/> must contain at least one character |
| ingredients | List of ingredients in form of `String[] array`,<br/> must contain at least one element |
| directions  | List of directions in form of `String[] array`,<br/> must contain at least one element  |

### GET

`GET /api/recipe/{id}` 

Returns a single recipe object with id value of `{id}`

Returns status `200 OK` if successful, status `404 Not Found` otherwise

| Path variable     | Description                         |
|-------------------|-------------------------------------|
| id                | index of the desired recipe, ex `1` |

Example:

`GET /api/recipe/1`

Returns:
```json
{
   "name": "Warming Ginger Tea",
   "description": "Ginger tea is a warming drink for cool weather, ...",
   "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
   "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
```

### Post

`POST /api/recipe/new`

Creates a new recipe and returns assigned id.

Accepts a request body in form of [Recipe object](#Recipe-object-format)

Returns status `200 OK` if successful. Returns `400 Bad Request` if object is not valid.

Example:

`POST /api/recipe/new`
```json
{
   "name": "Lemon Curd",
   "description": "A tasty citrussy topping",
   "ingredients": ["3 eggs", "3 egg yolks", "150 grams sugar", "150 grams lemon juice"],
   "directions": ["Whisk eggs, yolks and sugar together in a medium sauce pan", "Put on low heat, stir until thickened", "Add 1/3 of the lemon juice and stir until thickened again", "Add 1/3 lemon juice another two times and take off heat", "Serve"]
}
```

Returns:

```json
{
    "id": 2
}
```

### Delete

`DELETE /api/recipe/{id}`

Removes a recipe with `{id}` from the database.

If operation is successful returns status `204 No Content`. If the recipe was not found returns `404 Not Found`

Example 1:

`DELETE /api/recipe/1`

Returns:

`204 No Content`

Example 2:

`DELETE /api/recipe/100`, there is no recipe with `id=100`

Returns:

`404 Not Found`

### Pagination

`GET /api/recipe/page?page={page}&size={size}&sort={sort}`

Returns recipes in small chunks of size `{size}`, sorted by key `{sort}` and on page number `{page}`.

| Parameter | Description                                                                                              |
|-----------|----------------------------------------------------------------------------------------------------------|
| page      | (Optional) Number of the desired page, starts at `1`, default value is also `1`                          |
| size      | (Optional) Number of elements per page, default value is `20`                                            |
| sort      | (Optional) The key by which all recipes are sorted. Ascending or descending. Ex, `id,asc` or `name,desc` |
