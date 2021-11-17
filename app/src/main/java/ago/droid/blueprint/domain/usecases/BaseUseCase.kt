package ago.droid.blueprint.domain.usecases

interface BaseUseCase<TResponse, TParam> {
    suspend operator fun invoke(param: TParam) : TResponse
}